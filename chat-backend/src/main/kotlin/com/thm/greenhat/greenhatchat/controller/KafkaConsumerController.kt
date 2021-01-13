package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.model.Message
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import reactor.core.publisher.Sinks


@Serializable
data class WebSocketRequest(
       val text:String
)

@Controller
class KafkaConsumerController{
    val format = Json { encodeDefaults = true }

    val sink = Sinks.many().multicast().onBackpressureBuffer<String>()

    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message: Message) {
        this.sink.tryEmitNext(message.content)
    }

    @Bean
    fun handlerMapping(): HandlerMapping {
        val map = mapOf("/fromKafka" to
                WebSocketHandler { session->

                    println(session)
                    session.send(this.sink.asFlux().map{
                        val output = format.encodeToString(WebSocketRequest("green"))
                        session.textMessage(format.encodeToString(output))
                    })
                })
        return SimpleUrlHandlerMapping(map, -1)
    }
}