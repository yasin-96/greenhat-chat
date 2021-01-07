package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.model.Message
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class KafkaConsumerController{

    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message:Message) :Message{
        return sendToSocket(message)
    }

    @SendTo("/notify")
    fun sendToSocket(message: Message) : Message {
        return message
    }
}