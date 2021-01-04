package com.thm.greenhat.greenhatchat.service
/*
import com.thm.greenhat.greenhatchat.controller.GroupMessagesController
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.repository.GroupMessagesRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class GroupMessagesService(
        private val groupMessagesRepository: GroupMessagesRepository
) {


    fun findAllMessages(group: String) : Flux<Message> {
        return groupMessagesRepository.findById(group)
                .flatMapIterable { it.messages }
    }
}*/