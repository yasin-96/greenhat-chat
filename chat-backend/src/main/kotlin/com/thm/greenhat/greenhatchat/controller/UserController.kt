package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.User
import com.thm.greenhat.greenhatchat.service.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@CrossOrigin
@RestController
class UserController(private val userService: UserService) {
    
    //Do we really need this
    @GetMapping("/user/name/{username}")
    fun findByUsername(@PathVariable username: String): Mono<User> {
        return userService
                .findUserByUsername(username)
                .switchIfEmpty(Mono.error(BadRequestException("There is no user with such a name")))
    }

    //Do we really need this
    @GetMapping("/user/id/{id}")
    fun findUserById(@PathVariable id: String): Mono<User> {
        return userService.findById(id)
    }

    /**
     * Create new User
     * @param user User
     * @return Mono<User> or BadRequestException
     */
    @PostMapping("/user/register")
    fun addNewUser(@RequestBody user: User): Mono<User> {
        return userService.addUser(user)
    }

    /**
     *
     * @param username String
     * @param password String
     * @return Mono<User>
     */
    @GetMapping("/user/authenticate")
    fun login(@RequestParam username: String, @RequestParam password: String): Mono<User> {
        return userService.login(username, password)
    }

    /**
     * Update Specific information from user
     * @param username String
     * @param password String
     * @return Mono<User>
     */
    @PatchMapping("/user/specs/{userId}")
    fun patchSpecificUserInformation(@PathVariable userId: String, @RequestBody newUserSpecs: Map<String, Object>): Mono<User> {
        return userService.updateOnSpecificProperties(userId, newUserSpecs)
    }

}