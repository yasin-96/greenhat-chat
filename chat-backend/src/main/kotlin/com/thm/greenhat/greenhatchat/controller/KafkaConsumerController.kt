package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.model.Message
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.SynchronousSink
import reactor.kotlin.core.publisher.toFlux
import java.time.Duration

//@CrossOrigin
@Configuration
@EnableWebFlux
//@Controller
class KafkaConsumerController {

    val sink = Sinks.many().multicast().onBackpressureBuffer<String>()

    @Bean
    fun wsHandlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun handlerMapping(): HandlerMapping {
        return SimpleUrlHandlerMapping(
                mapOf("/fromKafka" to wsHandler()),
                -1
        )
    }

    fun wsHandler(): WebSocketHandler {
        return WebSocketHandler { session ->
            session.send(
                this.sink.asFlux().map {
                    println("Session: ${session.id}, \t Message> ${it}")
                    session.textMessage(it);
                }

            ).delayElement(Duration.ofSeconds(1))

        };
    }


    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message: Message) {
        this.sink.tryEmitNext(
                Json.encodeToString(message)
        )
    }
}