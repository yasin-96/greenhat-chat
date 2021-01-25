package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.exception.ConflictException
import com.thm.greenhat.greenhatchat.exception.NotFoundException
import com.thm.greenhat.greenhatchat.exception.NotModifiedException
import com.thm.greenhat.greenhatchat.model.User
import com.thm.greenhat.greenhatchat.model.UserToAddIntoGroup
import com.thm.greenhat.greenhatchat.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {

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
     * Try to find user on the email address
     * @param username String
     * @return Mono<User>
     *         Mono<NotFoundException> user not exist
     */
    fun findUserByEmail(email: String): Mono<User> {
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(NotFoundException("No user was found by email")))
    }

    fun findallUsers() : Flux<UserToAddIntoGroup> {
        return userRepository.findAll().map {
            UserToAddIntoGroup(it.id,it.username)
        }
    }


    fun checkUserNameAndEmail(username: String, email: String): Mono<Boolean> {
        return Mono.zip(
            findUserByUsername(username),
            findUserByEmail(email)
        ).flatMap {
            println("checkUserNameAndEmail ${it.t1.id}, ${it.t2.id}")
            if (it.t1.id.isNullOrBlank() && it.t2.id.isNullOrBlank()) {
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

    fun deleteAccount(id:String) : Mono<Void> {
        return userRepository.deleteById(id)
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
        return userRepository.existsByUsernameAndEmail(username,email)
                .map {
                    it
                }

    }

    fun changePassword(user:User,newPass:String) : Mono<User> {
        return userRepository.save(
                User(user.id,user.username,user.password,user.email,user.hasAvatarPicture,user.avatarName,user.avatarPicture)
        )
    }


    /**
     * Add User to database
     * @param user User
     * @return Mono<User> or  ConflictException if username/ email was found in database
     */
    fun addUser(user: User): Mono<User> {
        return checkUserNameAndEmailIfExist(user.username, user.email)
            .flatMap {
                if(it) Mono.error(ConflictException("This username/email already exists"))
                else userRepository.save(user)
                            .switchIfEmpty(Mono.error(NotModifiedException("Could not add user")))
                            .map { it }
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
    fun login(username: String, password: String): Mono<User> {
        return findUserByUsername(username)
            .flatMap {
                if (it.password == password) {
                    Mono.just(it)
                } else {
                    Mono.error(BadRequestException("Invalid Password or Username"))
                }
            }
    }

    /**
     *
     * @param userId String
     * @param specficInformation Map<String, Object>
     * @return Mono<User>
     */
    fun updateOnSpecificProperties(userId: String, specficInformation: Map<String, Object>): Mono<User> {
        return findById(userId)
            .map { user ->
                val userCopy = user.copy()
                println(specficInformation)
                for (specs in specficInformation!!) {
                    println(specs)
                    when (specs.key) {
                        "username" -> user.username = specs.value.toString()
                        "email" -> user.email = specs.value.toString()
                        "avatarPicture" -> user.avatarPicture = specs.value.toString()
                        "hasAvatarPicture" -> user.hasAvatarPicture = specs.value as Boolean
                    }
                    println("const = ${userCopy}, updated = ${user}")
                }
                println("const = ${userCopy}, updated = ${user}")
                arrayListOf(userCopy, user);
            }
            .flatMap {
                checkUserNameAndEmail(it[0].username, it[1].email)
                    .map { resultOfQuery -> resultOfQuery }
                    .filter { it ?: false }
                    .switchIfEmpty(Mono.error(ConflictException("Email or Username exist")))

                userRepository.save(it[1])
                    .switchIfEmpty(Mono.error(NotModifiedException("Could not update User information")))
                    .map {
                        it
                    }
            }
    }
}