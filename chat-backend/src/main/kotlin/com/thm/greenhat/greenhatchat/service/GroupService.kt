package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.exception.*
import com.thm.greenhat.greenhatchat.model.Group.GroupRequest
import com.thm.greenhat.greenhatchat.model.Group.GroupResponse
import com.thm.greenhat.greenhatchat.model.Group.GroupUserUpdate
import com.thm.greenhat.greenhatchat.model.User.UserToDisplay
import com.thm.greenhat.greenhatchat.repository.GroupRepository
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import com.thm.greenhat.greenhatchat.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GroupService(
    private val groupRepository: GroupRepository,
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository
) {

    /**
     *
     * @param id String
     * @return Mono<GroupResponse>
     */
    fun findGroupById(id: String): Mono<GroupResponse> {
        var groupInfo = GroupRequest("", "", "", mutableListOf<String>())

        return groupRepository.findById(id)
            .map {
                groupInfo = it
                userRepository.findAllById(it.users)
                    .collectList()
                    .map { it }
            }
            .flatMap {
                it.map { userList ->
                    val userRep = arrayListOf<UserToDisplay>()
                    userList.forEach { user ->
                        userRep.add(UserToDisplay(user.id, user.username, user.avatarPicture, user.avatarName))
                    }
                    userRep
                }.zipWith(userRepository.findById(groupInfo.admin))
            }.map {
                GroupResponse(groupInfo._id, groupInfo.name, it.t2.username, it.t1, groupInfo.groupColor)
            }
    }

    /**
     *
     * @param id String
     * @return Mono<GroupRequest>
     */
    fun findAllGroupMessages(id: String): Mono<GroupRequest> {
        return Mono.zip(groupRepository.findById(id), messageRepository.findMessageByGroupId(id).collectList())
            .map {
                it.t1.messages.addAll(it.t2)
                it.t1
            }
    }

    /**
     *
     * @param group GroupRequest
     * @return Mono<GroupRequest>
     */
    fun addGroup(group: GroupRequest): Mono<GroupRequest> {

        //add admin also to user list
        group.users.add(group.admin)


        return checkIfGroupNameExists(group.name)
            .switchIfEmpty(Mono.error(ConflictException("test")))
            .flatMap {
                if (it) Mono.error(ConflictException("This groupname already exists."))
                else groupRepository.save(group)
                    .switchIfEmpty(Mono.error(NotModifiedException("Could not add group")))
            }
    }

    /**
     *
     * @param name String
     * @return Mono<Boolean>
     */
    fun checkIfGroupNameExists(name: String): Mono<Boolean> {
        return groupRepository.existsByName(name).map {
            it
        }
    }

    /**
     *
     * @param userId String
     * @return Mono<List<GroupRequest>>
     */
    fun findGroupsFromUser(userId: String): Mono<List<GroupRequest>> {
        return groupRepository.findAll().collectList()
            .map { allGroups ->
                allGroups.filter { group ->
                    group.admin == userId ||
                            group.users.contains(userId)

                }
            }
    }


    fun removeUserFromGroup(groupUserUpdate: GroupUserUpdate): Mono<GroupResponse> {
        return groupRepository.findById(groupUserUpdate.groupId)
            .switchIfEmpty(Mono.error(NotFoundException("Group not found")))
            .flatMap {
                if(groupUserUpdate.userId == it.admin){
                    Mono.error(NotAcceptableException("Admin can not be deleted here"))
                } else {
                    it.users.remove(groupUserUpdate.userId)
                    groupRepository.save(it)
                        .switchIfEmpty(Mono.error(NotModifiedException()))
                }
            }.flatMap {
                findGroupById(it._id).map { it }
            }
    }

    fun addUserToGroup(groupUserUpdate: GroupUserUpdate): Mono<GroupResponse> {
        return groupRepository.findById(groupUserUpdate.groupId)
            .switchIfEmpty(Mono.error(NotFoundException("Group not found")))
            .flatMap {
                it.users.addAll(groupUserUpdate.userIds!!.map { it })
                groupRepository.save(it)
                    .switchIfEmpty(Mono.error(NotModifiedException()))
            }.flatMap {
                findGroupById(it._id).map { it }
            }
    }

    fun updateOnSpecificProperties(groupId: String, specificInformation: Map<String, Object>): Mono<GroupResponse>{
        return groupRepository.findById(groupId)
            .map { group ->
                println(specificInformation)
                for (specs in specificInformation) {
                    println(specs)
                    when (specs.key) {
                        "name" -> group.name = specs.value.toString()
                        "admin" -> group.admin = specs.value.toString()
                        "groupColor" -> group.groupColor = specs.value.toString()
                    }
                    println("updated = $group")
                }
                group
            }
            .flatMap {
                groupRepository.save(it)
                    .switchIfEmpty(Mono.error(NotModifiedException("Could not update Group information")))
                    .map {
                        it
                    }
            }.flatMap {
                findGroupById(it._id).map { it }
            }
    }
}