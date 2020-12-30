package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.repository.GroupMessagesRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.*

@RestController
class GroupMessagesController(
        private val groupMessageRepository: GroupMessagesRepository
) {
    @GetMapping("/messages/[{group}")
    fun findAllMessages(@PathVariable group: UUID) : Flux<Message> {
        return groupMessageRepository.findById(group)
                .flatMapIterable { it.messages }
    }

}