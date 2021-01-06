package com.thm.greenhat.greenhatchat.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document("messages")
data class Message(
        @Id var id: String = ObjectId.get().toString(),
        var content: String,
        var created:Long = Instant.now().epochSecond * 1000,
        var userId:String,
        var groupId:String
)