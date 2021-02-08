package com.thm.greenhat.greenhatchat.repository

import com.thm.greenhat.greenhatchat.model.group.GroupRequest
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface GroupRepository : ReactiveCrudRepository<GroupRequest, String> {

    fun findByName(name: String): Mono<GroupRequest>
    fun existsByName(name: String): Mono<Boolean>
    fun findAllByAdmin(admin:String) : Flux<GroupRequest>
    fun existsByAdmin(admin: String) : Flux<Boolean>
}