package com.thm.greenhat.greenhatchat.repository

import com.thm.greenhat.greenhatchat.model.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface UserRepository : ReactiveCrudRepository<User,UUID> {
    fun findByUsername(username:String): Mono<User>
}