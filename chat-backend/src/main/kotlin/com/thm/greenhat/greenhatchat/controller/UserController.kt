package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.User
import com.thm.greenhat.greenhatchat.service.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
class UserController(
  private val userService: UserService
) {
    @GetMapping("/user/name/{username}")
    fun findByUsername(@PathVariable username:String) : Mono<User>
    { return userService.findUserByUsername(username)
            .switchIfEmpty(Mono.error(BadRequestException("There is no user with such a name")))
    }

    @GetMapping("/user/id/{id}")
    fun findUserById(@PathVariable id:String) : Mono<User>
    {return userService.findById(id)}

    @PostMapping("/user/register")
    fun addNewUser(@RequestBody user:User) : Mono<User>{
        return userService.addUser(user)
    }

    @GetMapping("/user/authenticate")
    fun login(@RequestParam username:String, @RequestParam password:String) : Mono<User>{
        return userService.login(username,password)
    }
}