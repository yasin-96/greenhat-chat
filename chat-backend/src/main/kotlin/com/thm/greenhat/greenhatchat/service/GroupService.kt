package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.GroupController
import com.thm.greenhat.greenhatchat.exception.ConflictException
import com.thm.greenhat.greenhatchat.exception.NotModifiedException
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
        var groupInfo = GroupRequest("","","", mutableListOf<String>())
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
                                    userList.forEach{ user ->
                                        userRep.add(UserToDisplay(user.id,user.username,user.avatarPicture,user.avatarName))
                                    }
                               GroupResponse(groupInfo._id, groupInfo.name, groupInfo.admin, userRep)
                            }
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
        return checkIfGroupNameExists(group.name)
                .flatMap{
                    if(it){
                        groupRepository.save(group)
                                .switchIfEmpty(Mono.error(NotModifiedException("Could not add group")))
                                .map { it }
                    }
                    else {
                        Mono.error(ConflictException("This groupname already exists."))
                    }
                }
    }

    fun checkIfGroupNameExists(name:String) : Mono<Boolean>{
        return groupRepository.findByName(name)
                .map {
                    it == null
                }

    }



//    fun findGroupsFromUser(userId: String): Mono<GroupResponse>{
//        var groupReponses = arrayListOf<GroupRequest>()
//        return groupRepository.findAll().collectList()
//            .map{ allGroups ->
//                var userInGroup = arrayListOf<GroupRequest>()
//                allGroups.map { group ->
//                    if(group.users.find { it == userId }.toString().isNotEmpty()){
//                        userInGroup.add(group)
//                    }
//
//                }
//                groupReponses = userInGroup
//                userInGroup
//            }
//            .map { userGroups ->
//                userGroups.map {
//                    userRepository.findAllById(it.users)
//                        .collectList()
//                        .map { it }
//                }
//            }
//            .map {
//
//            }
//
//
//
////
////                    gList ->
////                var groupResult = arrayListOf<GroupResponse>()
////                gList.map { uList ->
////
////                    uList.map {
////
////                    val userRep = arrayListOf<UserToDisplay>()
////                    userList.forEach{ user ->
////                        userRep.add(UserToDisplay(user.id,user.username,user.avatarPicture,user.avatarName))
////                    }
////                    GroupResponse(groupInfo._id, groupInfo.name, groupInfo.admin, userRep)
////                }
////
////                }
////
////                it.map {
////
////
////                        userList ->
////                    val userRep = arrayListOf<UserToDisplay>()
////                    userList.forEach{ user ->
////                        userRep.add(UserToDisplay(user.id,user.username,user.avatarPicture,user.avatarName))
////                    }
////                    GroupResponse(groupInfo._id, groupInfo.name, groupInfo.admin, userRep)
////                }
//
//            }
//    }
}