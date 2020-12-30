package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import reactor.core.publisher.Mono
import java.util.*

@RestController
class MessageController(
        private val messageRepository: MessageRepository
) {

    @GetMapping("/message/{id}")
    fun findById(id:UUID) : Mono<Message> {
        return messageRepository.findById(id)
                .switchIfEmpty(Mono.error(BadRequestException("There is no messeage with such an id")))
    }
}