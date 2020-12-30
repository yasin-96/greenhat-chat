package com.thm.greenhat.greenhatchat.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("message")
data class Message(
        @Id var id: UUID?,
        var content: String,
        var created:Long,
        var userId:UUID
)