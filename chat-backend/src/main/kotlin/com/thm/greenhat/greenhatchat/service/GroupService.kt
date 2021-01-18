package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.GroupController
import com.thm.greenhat.greenhatchat.model.*
import com.thm.greenhat.greenhatchat.repository.GroupRepository
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import com.thm.greenhat.greenhatchat.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class GroupService(
        private val groupRepository: GroupRepository,
        private val messageRepository: MessageRepository,
        private val userRepository: UserRepository
) {

    fun findGroupById(id: String) : Mono<GroupResponse> {
        return groupRepository.findById(id)
                .map { groupRequest ->
                    val list = mutableListOf<Mono<User>>()
                    groupRequest.users.forEach{
                       list.add(userRepository.findById(it))
                    }
    }

   fun findAllGroupMessages(id:String) : Mono<GroupRequest> {
        return Mono.zip(groupRepository.findById(id),messageRepository.findMessageByGroupId(id).collectList())
                .map {
                    it.t1.messages.addAll(it.t2)
                    it.t1
                }
    }


    fun addGroup(group:GroupRequest) : Mono<GroupRequest> {
        return groupRepository.save(group)
    }
}