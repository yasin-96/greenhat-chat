package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.UserController
import com.thm.greenhat.greenhatchat.model.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
        private val userController: UserController
) {

    fun findUserByUsername(username :String) : Mono<User> {
        return userController.findByUsername(username)
    }
}