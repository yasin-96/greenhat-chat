package com.thm.greenhat.greenhatchat.repository

import com.thm.greenhat.greenhatchat.model.Message
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface MessageRepository : ReactiveCrudRepository<Message, String> {

    fun findMessageByGroupId(groupId: String): Flux<Message>
}