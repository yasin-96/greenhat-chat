package com.thm.greenhat.greenhatchat.repository

import com.thm.greenhat.greenhatchat.model.User
import com.thm.greenhat.greenhatchat.model.UserToDisplay
import org.springframework.data.mongodb.repository.Query
import java.util.*
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.collections.ArrayList

@Repository
interface UserRepository : ReactiveCrudRepository<User, String> {
    fun findByUsername(username: String): Mono<User>
    fun findByEmail(email: String): Mono<User>
    fun findUserNameById(id:String) :Mono<String>
    fun findByUsernameAndEmail(username: String, email: String): Flux<User>
//    fun findAllById(ids: MutableList<String>): Flux<UserToDisplay>
}