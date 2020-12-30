package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.GroupMessagesController
import com.thm.greenhat.greenhatchat.model.Message
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.*

@Service
class GroupMessagesService(
        private val groupMessagesController: GroupMessagesController
) {

    fun findAllMessages(group: UUID) : Flux<Message> {
        return groupMessagesController.findAllMessages(group)
    }
}