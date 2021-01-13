package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.model.Message
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import reactor.core.publisher.Sinks


@Controller
class KafkaConsumerController{

    val sink = Sinks.many().multicast().onBackpressureBuffer<String>()


    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message: Message) {
        this.sink.tryEmitNext(message.content)
    }

    @Bean
    fun handlerMapping(): HandlerMapping {
        val map = mapOf("/fromKafka" to
                WebSocketHandler { session ->
                    session.send(this.sink.asFlux().map(session::textMessage))
                })
        return SimpleUrlHandlerMapping(map, -1)
    }


}