package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.model.Message
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import reactor.core.publisher.Sinks

@Controller
class KafkaConsumerController {

    val sink = Sinks.many().multicast().onBackpressureBuffer<String>()

    @Bean
    fun handlerMapping(): HandlerMapping {
        val map = mapOf("/fromKafka" to
                WebSocketHandler { session ->

                    session.send(this.sink.asFlux().map {
                        println("Session: ${session.id}, \t Message> ${it}")
                        session.textMessage(it);
                    })
                })
        return SimpleUrlHandlerMapping(map, -1)
    }

    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message: Message) {
        val jsonObject = Json.encodeToString<Message>(message);
        this.sink.tryEmitNext(jsonObject)
    }
}