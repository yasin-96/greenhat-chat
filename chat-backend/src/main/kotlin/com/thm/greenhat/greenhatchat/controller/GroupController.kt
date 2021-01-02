package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.Group
import com.thm.greenhat.greenhatchat.service.GroupService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class GroupController(
        private val groupService: GroupService
) {
    @GetMapping("/group/{id}")
    fun findGroupById(id:String) : Mono<Group> {
        return groupService.findGroupById(id)
                .switchIfEmpty(Mono.error(BadRequestException("There is no group with such an id")))
    }



}