package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.GenericMessage
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*
import java.util.logging.Logger
import kotlin.collections.HashMap

@RestController
class MessageController(
        private val messageRepository: MessageRepository,
        private val kafkaTemplate: KafkaTemplate<String, String>
)


{
    private val LOGGER: org.slf4j.Logger? = LoggerFactory.getLogger(MessageController::class.java)

    private val topicName: String = "mytopic"

    @GetMapping("/message/{id}")
    fun findById(@PathVariable id: String): Mono<Message> {
        return messageRepository.findById(id)
                .switchIfEmpty(Mono.error(BadRequestException("There is no messeage with such an id")))
    }

    @PostMapping("/message")
    fun addMessage(@RequestBody message: Message){
        val headers : MutableMap<String, Any> = HashMap()
        headers[KafkaHeaders.TOPIC] = topicName
        kafkaTemplate.send(GenericMessage<Message>(message, headers))

    }
}