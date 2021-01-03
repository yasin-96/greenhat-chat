package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.GroupController
import com.thm.greenhat.greenhatchat.model.Group
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.repository.GroupRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class GroupService(
        private val groupRepository: GroupRepository
) {

    fun findGroupById(id: String) : Mono<Group> {
        return groupRepository.findById(id)
    }

    fun findAllGroupMessages(id:String) : Flux<Message> {
        return groupRepository.findById(id).
                flatMapIterable { it.messages }
    }

    fun addGroup(group:Group) : Mono<Group> {
        return groupRepository.save(group)
    }
}