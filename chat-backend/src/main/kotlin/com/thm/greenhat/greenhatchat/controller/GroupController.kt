package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.Group
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.service.GroupService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin
@RestController
class GroupController(
        private val groupService: GroupService
) {
    @GetMapping("/group/{id}")
    fun findGroupById(@PathVariable id:String) : Mono<Group> {
        return groupService.findGroupById(id)
                .switchIfEmpty(Mono.error(BadRequestException("There is no group with such an id")))
    }

    @GetMapping("/{group}/messages")
    fun findAllGroupMessages(@PathVariable group:String) : Mono<Group> {
        return groupService.findAllGroupMessages(group)
    }

    @PostMapping("/group")
    fun addGroup(@RequestBody group:Group) : Mono<Group> {
        return groupService.addGroup(group)
    }



}