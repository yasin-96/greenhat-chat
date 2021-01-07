package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.GroupController
import com.thm.greenhat.greenhatchat.model.Group
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.repository.GroupRepository
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class GroupService(
        private val groupRepository: GroupRepository,
        private val messageRepository: MessageRepository
) {

    fun findGroupById(id: String) : Mono<Group> {
        return groupRepository.findById(id)
    }

   fun findAllGroupMessages(id:String) : Mono<Group> {
        return Mono.zip(groupRepository.findById(id),messageRepository.findMessageByGroupId(id).collectList())
                .map {
                    it.t1.messages.addAll(it.t2)
                    it.t1
                }
    }


    fun addGroup(group:Group) : Mono<Group> {
        return groupRepository.save(group)
    }
}