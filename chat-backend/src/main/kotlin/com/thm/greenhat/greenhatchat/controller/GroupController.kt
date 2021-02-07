package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.group.GroupRequest
import com.thm.greenhat.greenhatchat.model.group.GroupResponse
import com.thm.greenhat.greenhatchat.model.group.GroupUserUpdate
import com.thm.greenhat.greenhatchat.repository.GroupRepository
import com.thm.greenhat.greenhatchat.service.GroupService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@CrossOrigin
@RestController
class GroupController(
    private val groupService: GroupService,
    private val groupRepository: GroupRepository
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
     * @return Mono<GroupResponse>
     */
    @DeleteMapping("/group/id/{id}")
    fun deleteGroupById(@PathVariable id: String): Mono<Void> {
        return groupService.deleteGroupById(id)
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

    @DeleteMapping("/group/user")
    fun removeUserFromGroup(@RequestBody groupUserUpdate: GroupUserUpdate): Mono<GroupResponse> {
        return groupService.removeUserFromGroup(groupUserUpdate)
    }

    @PutMapping("/group/users")
    fun addUserToGroup(@RequestBody groupUserUpdate: GroupUserUpdate): Mono<GroupResponse> {
        return groupService.addUserToGroup(groupUserUpdate)
    }


    @PatchMapping("/group/specs/{groupId}")
    fun patchSpecificGroupInformation(
        @PathVariable groupId: String,
        @RequestBody groupUpdate: Map<String, Any>
    ): Mono<GroupResponse> {
        return groupService.updateOnSpecificProperties(groupId, groupUpdate)
    }

}