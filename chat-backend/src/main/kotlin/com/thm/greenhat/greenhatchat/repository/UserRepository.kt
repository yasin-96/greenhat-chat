package com.thm.greenhat.greenhatchat.repository

import com.thm.greenhat.greenhatchat.model.user.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<User, String> {
    fun findByUsername(username: String): Mono<User>
    fun findByEmail(email: String): Mono<User>
    fun existsByUsernameAndEmail(username: String, email: String): Mono<Boolean>
}