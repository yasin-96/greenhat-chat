package com.thm.greenhat.greenhatchat.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


@Document("groupmessages")
data class GroupMessages(
        @Id var groupId : UUID,
        var messages: MutableList<Message> = mutableListOf()
)

