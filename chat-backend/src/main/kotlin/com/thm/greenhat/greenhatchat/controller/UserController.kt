package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.ChangePasswordRequest
import com.thm.greenhat.greenhatchat.model.group.GroupRequest
import com.thm.greenhat.greenhatchat.model.user.User
import com.thm.greenhat.greenhatchat.model.user.UserForUI
import com.thm.greenhat.greenhatchat.model.user.UserToAddIntoGroup
import com.thm.greenhat.greenhatchat.service.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin
@RestController
class UserController(
        private val userService: UserService,

        ) {

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

    @GetMapping("/user/allUsers")
    fun findAllUsers(): Flux<UserToAddIntoGroup> {
        return userService.findAllUsers()
    }

    /**
     * Create new User
     * @param user User
     * @return Mono<User> or BadRequestException
     */
    @PostMapping("/user/register")
    fun addNewUser(@RequestBody user: User): Mono<UserForUI> {
        return userService.addUser(user)
    }

    /**
     *
     * @param username String
     * @param password String
     * @return Mono<User>
     */
    @GetMapping("/user/authenticate")
    fun login(@RequestParam username: String, @RequestParam password: String): Mono<UserForUI> {
        return userService.login(username, password)
    }

    /**
     *
     * @param id String
     * @return Mono<Void>
     */
    @DeleteMapping("/user/{id}")
    fun deleteAccount(@PathVariable id: String): Flux<Void> {
        return userService.deleteAccount(id)
    }

    /**
     *
     * @param id String
     * @param request ChangePasswordRequest
     * @return Mono<User>
     */
    @PutMapping("/user/{id}")
    fun changePassword(@PathVariable id: String, @RequestBody changePasswordRequest: ChangePasswordRequest): Mono<UserForUI> {
        return userService.tryToChangePassworFromUser(id, changePasswordRequest)
    }

    /**
     *
     * @param id String
     * @return Mono<MutableSet<String>>
     */
    @GetMapping("/user/groups/{id}")
    fun getGroupsFromUser(@PathVariable id: String): Mono<MutableSet<String>> {
        return userService.getGroupsFromUser(id)
    }


    /**
     * Update Specific information from user
     * @param username String
     * @param password String
     * @return Mono<User>
     */
    @PatchMapping("/user/specs/{userId}")
    fun patchSpecificUserInformation(
        @PathVariable userId: String,
        @RequestBody newUserSpecs: Map<String, Any>
    ): Mono<UserForUI> {
        return userService.updateOnSpecificProperties(userId, newUserSpecs)
    }

}