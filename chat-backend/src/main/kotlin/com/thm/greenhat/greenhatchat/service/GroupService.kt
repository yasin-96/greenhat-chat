package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.GroupController
import com.thm.greenhat.greenhatchat.model.Group
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class GroupService(
        private val groupController: GroupController
) {

    fun findGroupById(id: UUID) : Mono<Group> {
        return groupController.findGroupById(id)
    }
}