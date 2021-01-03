package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.model.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
class KafkaConsumerService(
        private val messageService: MessageService
) {
    private val logger: Logger = LoggerFactory.getLogger(KafkaConsumerService::class.java)

    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message: Message) {

    }

}