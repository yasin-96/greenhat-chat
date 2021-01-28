package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.exception.ConflictException
import com.thm.greenhat.greenhatchat.exception.NotModifiedException
import com.thm.greenhat.greenhatchat.model.*
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
                GroupResponse(groupInfo._id, groupInfo.name, it.t2.username, it.t1)
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
}