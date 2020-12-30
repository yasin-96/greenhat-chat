package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.User
import com.thm.greenhat.greenhatchat.repository.UserRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*

@RestController
class UserController(
  private val userRepository: UserRepository
) {

    @GetMapping("/user/{username}")
    fun findByUsername(@PathVariable username:String) : Mono<User>
    { return userRepository.findByUsername(username)
            .switchIfEmpty(Mono.error(BadRequestException("There is no user with such a name")))
    }

    @GetMapping("/user/{id}")
    fun findUserById(@PathVariable id:UUID) : Mono<User>
    {return userRepository.findById(id)
            .switchIfEmpty(Mono.error(BadRequestException("There is np user with such an id")))}

    @PostMapping("(/user/register")
    fun addNewUser(@RequestBody user:User) : Mono<UUID>{
        return userRepository.save(user)
                .map { it.id!! }
    }

    @GetMapping("/user/authenticate")
    fun login(@RequestParam username: String, @RequestParam password:String) : Mono<User> {
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