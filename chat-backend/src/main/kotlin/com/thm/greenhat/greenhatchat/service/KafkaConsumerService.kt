package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.model.Message
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/*
@Service
class KafkaConsumerService{

    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message: Message) : Message{
        return message
    }

}*/