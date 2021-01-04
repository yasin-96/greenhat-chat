package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.controller.UserController
import com.thm.greenhat.greenhatchat.model.User
import com.thm.greenhat.greenhatchat.repository.UserRepository
import org.springframework.lang.UsesJava7
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Mono

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun findUserByUsername(username :String) : Mono<User> {
        return userRepository.findByUsername(username)
    }

    fun findById(id:String) : Mono<User>{
        return userRepository.findById(id)
    }

    fun addUser(user:User) : Mono<User> {
        return userRepository.save(user)
                .map{it}
    }

    fun login(username: String, password:String) : Mono<User> {
        return userRepository.findByUsername(username)
                .flatMap {
                    if(it.password == password){
                        Mono.just(it)
                    }
                    else {
                        Mono.empty<User>()
                    }
                }
    }
}