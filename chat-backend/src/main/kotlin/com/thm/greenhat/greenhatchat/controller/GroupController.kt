package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.Group
import com.thm.greenhat.greenhatchat.repository.GroupRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class GroupController(
        private val groupRepository: GroupRepository
) {
    @GetMapping("/group/{id}")
    fun findGroupById(id:UUID) : Mono<Group> {
        return groupRepository.findById(id)
                .switchIfEmpty(Mono.error(BadRequestException("There is no group with such an id")))
    }



}