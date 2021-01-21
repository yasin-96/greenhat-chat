package com.thm.greenhat.greenhatchat.repository

import com.thm.greenhat.greenhatchat.model.GroupRequest
import org.apache.kafka.common.protocol.types.Field
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface GroupRepository : ReactiveCrudRepository<GroupRequest,String> {

    fun findByName(name:String) : Mono<GroupRequest>

}