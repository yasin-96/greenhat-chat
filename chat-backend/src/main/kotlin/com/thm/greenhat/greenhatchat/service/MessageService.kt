package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.MessageController
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class MessageService(
        private val messageController: MessageController
) {
    fun findMessageById(id:UUID) : Mono<Message> {
        return messageController.findById(id)
    }
}