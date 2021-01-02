package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.GroupController
import com.thm.greenhat.greenhatchat.model.Group
import com.thm.greenhat.greenhatchat.repository.GroupRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GroupService(
        private val groupRepository: GroupRepository
) {

    fun findGroupById(id: String) : Mono<Group> {
        return groupRepository.findById(id)
    }
}