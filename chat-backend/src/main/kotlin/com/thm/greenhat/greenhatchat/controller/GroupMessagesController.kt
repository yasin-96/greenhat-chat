package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.service.GroupMessagesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
class GroupMessagesController(
        private val groupMessagesService: GroupMessagesService
) {
    @GetMapping("/messages/[{group}")
    fun findAllMessages(@PathVariable group: String) : Flux<Message> {
        return groupMessagesService.findAllMessages(group)
    }

}