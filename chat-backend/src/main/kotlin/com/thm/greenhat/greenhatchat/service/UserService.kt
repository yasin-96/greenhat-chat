package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.exception.ConflictException
import com.thm.greenhat.greenhatchat.exception.NotFoundException
import com.thm.greenhat.greenhatchat.exception.NotModifiedException
import com.thm.greenhat.greenhatchat.model.ChangePasswordRequest
import com.thm.greenhat.greenhatchat.model.group.GroupRequest
import com.thm.greenhat.greenhatchat.model.user.User
import com.thm.greenhat.greenhatchat.model.user.UserForUI
import com.thm.greenhat.greenhatchat.model.user.UserToAddIntoGroup
import com.thm.greenhat.greenhatchat.repository.GroupRepository
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import com.thm.greenhat.greenhatchat.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono

@Service
class UserService(
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository,
    private val messageRepository: MessageRepository,
    private val passwordEncoder: PasswordEncoder
) {

    /**
     * Try to find user on the username
     * @param username String
     * @return Mono<User> or
     *         Mono<NotFoundException> user not exist
     */
    fun findUserByUsername(username: String): Mono<User> {
        return userRepository.findByUsername(username)
            .switchIfEmpty(Mono.error(NotFoundException("No user was found by username")))
    }



    /**
     *
     * @param email String
     * @return Mono<User>
     */
    fun findUserByEmail(email: String): Mono<User> {
        return userRepository.findByEmail(email)
            .switchIfEmpty(Mono.error(NotFoundException("No user was found by email")))
    }

    /**
     *
     * @return Flux<UserToAddIntoGroup>
     */
    fun findAllUsers(): Flux<UserToAddIntoGroup> {
        return userRepository.findAll().map {
            UserToAddIntoGroup(it.id, it.username)
        }
    }


    /**
     *
     * @param username String
     * @param email String
     * @return Mono<Boolean>
     */
    fun checkUserNameAndEmail(username: String, email: String): Mono<Boolean> {
        return Mono.zip(
            findUserByUsername(username),
            findUserByEmail(email)
        ).flatMap {
            println("checkUserNameAndEmail ${it.t1.id}, ${it.t2.id}")
            if (it.t1.id.isBlank() && it.t2.id.isBlank()) {
                Mono.just(true)
            }
            Mono.just(false)
        }
    }

    /**
     * Try to find user on the id
     * @param id String
     * @return Mono<User>
     *         Mono<NotFoundException> user not exist
     */
    fun findById(id: String): Mono<User> {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(NotFoundException("No user was found based on the id")))
    }

    /**
     *
     * @param id String
     * @return Mono<Void>
     */
    fun deleteAccount(userId: String) : Flux<Void> {
        return groupRepository.findAllByAdmin(userId)
                .flatMap { group ->
                    if (group.users.size  < 2) {
                        Mono.zip(messageRepository.deleteMessageByUserId(userId), userRepository.deleteById(userId),groupRepository.deleteById(group._id)).map {
                            it.t1
                        }
                    } else {
                        group.admin = group.users[0]
                        group.users.remove(userId)

                        Mono.zip(messageRepository.deleteMessageByUserId(userId), userRepository.deleteById(userId),groupRepository.save(group)).map {it.t1 }
                    }
                }
    }



    /**
     * It will check, if the username and email already exist
     * @param username String
     * @param email String
     * @return Mono<Boolean>
     *         true if not found
     *         false user was found
     */
    fun checkUserNameAndEmailIfExist(username: String, email: String): Mono<Boolean> {
        return userRepository.existsByUsernameAndEmail(username, email)
            .map {
                it
            }
    }

    /**
     *
     * @param userId String
     * @param passwordChanges ChangePasswordRequest
     * @return Mono<UserForUI>
     */
    fun tryToChangePassworFromUser(userId: String, passwordChanges: ChangePasswordRequest): Mono<UserForUI> {
        return Mono.zip(
            checkIfOldPasswordCorrect(userId, passwordChanges.oldPassInput),
            findById(userId)
        )
            .filter {
                it.t1
            }.switchIfEmpty(
                Mono.error(BadRequestException("Old password was wrong."))
            )
            .flatMap {
                changePassword(it.t2, passwordChanges.newPass)
            }

    }


    /**
     *
     * @param id String
     * @param oldPass String
     * @return Mono<Boolean>
     */
    fun checkIfOldPasswordCorrect(id: String, oldPass: String): Mono<Boolean> {
        return findById(id)
            .map {
                it.password == oldPass
            }
    }

    /**
     *
     * @param user User
     * @param newPass String
     * @return Mono<User>
     */
    fun changePassword(user: User, newPass: String): Mono<UserForUI> {
        return userRepository.save(
            User(
                user.id,
                user.username,
                newPass,
                user.email,
                user.hasAvatarPicture,
                user.avatarName,
                user.avatarPicture
            )
        ).map {
            UserForUI(
                user.id,
                user.username,
                user.email,
                user.hasAvatarPicture,
                user.avatarName,
                user.avatarPicture
            )
        }
    }


    /**
     * Add User to database
     * @param user User
     * @return Mono<User> or  ConflictException if username/ email was found in database
     */
    fun addUser(user: User): Mono<UserForUI> {
        return checkUserNameAndEmailIfExist(user.username, user.email)
            .flatMap {
                if (it) Mono.error(ConflictException("This username/email already exists"))
                else
                {
                    user.password = passwordEncoder.encode(user.password)
                    userRepository.save(user)
                            .switchIfEmpty(Mono.error(NotModifiedException("Could not add user")))
                            .map { UserForUI(it.id,it.username,it.email,it.hasAvatarPicture,it.avatarName) }
                }
            }
    }

    /**
     * Try to log User in
     * @param username String
     * @param password String
     * @return Mono<User> or
     *         Mono<NotFoundException> if user not exist with username+password or
     *         Mono<BadRequestException> if username or password are invalid
     */
    fun login(username: String, password: String): Mono<UserForUI> {
        return findUserByUsername(username)
            .flatMap {
                if (passwordEncoder.matches(password,it.password) ) {
                    Mono.just(UserForUI(it.id,it.username,it.email,it.hasAvatarPicture,it.avatarName,it.avatarPicture))
                } else {
                    Mono.error(BadRequestException("Invalid Password or Username"))
                }
            }
    }

    /**
     *
     * @param userId String
     * @param specificInformation Map<String, Object>
     * @return Mono<User>
     */
    fun updateOnSpecificProperties(userId: String, specificInformation: Map<String, Any>): Mono<UserForUI> {
        return findById(userId)
            .map { user ->
                val userCopy = user.copy()
                println(specificInformation)
                for (specs in specificInformation) {
                    println(specs)
                    when (specs.key) {
                        "username" -> user.username = specs.value.toString()
                        "email" -> user.email = specs.value.toString()
                        "avatarPicture" -> user.avatarPicture = specs.value.toString()
                        "hasAvatarPicture" -> user.hasAvatarPicture = specs.value as Boolean
                    }
                    println("const = ${userCopy}, updated = $user")
                }
                println("const = ${userCopy}, updated = $user")
                arrayListOf(userCopy, user)
            }
            .flatMap {
                checkUserNameAndEmail(it[0].username, it[1].email)
                    .map { resultOfQuery -> resultOfQuery }
                    .filter { it ?: false }
                    .switchIfEmpty(Mono.error(ConflictException("Email or Username exist")))

                userRepository.save(it[1])
                    .switchIfEmpty(Mono.error(NotModifiedException("Could not update User information")))
                    .map {
                        UserForUI(it.id,it.username,it.email,it.hasAvatarPicture,it.avatarName,it.avatarPicture)
                    }
            }
    }

    /**
     *
     * @param id String
     * @return Mono<MutableSet<String>>
     */
    fun getGroupsFromUser(id: String): Mono<MutableSet<String>> {
        return groupRepository.findAll().collectList()
            .map { groups ->
                val list = mutableSetOf<String>()
                groups.map { group ->
                    group.users.filter { it == id }
                        .map {
                            list.add(group._id)
                        }
                }
                list
            }
    }
}