package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.GroupRequest
import com.thm.greenhat.greenhatchat.model.GroupResponse
import com.thm.greenhat.greenhatchat.service.GroupService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@CrossOrigin
@RestController
class GroupController(
    private val groupService: GroupService
) {
    /**
     *
     * @param id String
     * @return Mono<GroupResponse>
     */
    @GetMapping("/group/id/{id}")
    fun findGroupById(@PathVariable id: String): Mono<GroupResponse> {
        return groupService.findGroupById(id)
            .switchIfEmpty(Mono.error(BadRequestException("There is no group with such an id")))
    }

    /**
     *
     * @param id String
     * @return Mono<GroupRequest>
     */
    @GetMapping("/group/messages/{id}")
    fun findAllGroupMessages(@PathVariable id: String): Mono<GroupRequest> {
        return groupService.findAllGroupMessages(id)
    }

    /**
     *
     * @param groupRequest GroupRequest
     * @return Mono<GroupRequest>
     */
    @PostMapping("/group/new")
    fun addGroup(@RequestBody groupRequest: GroupRequest): Mono<GroupRequest> {
        return groupService.addGroup(groupRequest)
    }

    /**
     *
     * @param id String
     * @return Mono<List<GroupRequest>>
     */
    @GetMapping("/group/user/{id}")
    fun findAllGroupsFromUser(@PathVariable id: String): Mono<List<GroupRequest>> {
        return groupService.findGroupsFromUser(id)
    }

}