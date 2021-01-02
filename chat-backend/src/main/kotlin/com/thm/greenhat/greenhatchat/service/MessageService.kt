package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.MessageController
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class MessageService(
        private val messageRepository: MessageRepository,
        private val kafkaTemplate: KafkaTemplate<String, String>
) {
    private val topicName: String = "mytopic"

    fun findMessageById(id:String) : Mono<Message> {
        return messageRepository.findById(id)
    }

    fun addMessage(message: Message){
            messageRepository.save(message).map {
            val headers : MutableMap<String, Any> = HashMap()
            headers[KafkaHeaders.TOPIC] = topicName
            kafkaTemplate.send(GenericMessage<Message>(message, headers))
        }

    }
}