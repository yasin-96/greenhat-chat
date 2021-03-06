package com.thm.greenhat.greenhatchat.model.group

import com.thm.greenhat.greenhatchat.model.Message
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document


@Document("group")
data class GroupRequest(
    @Id var _id: String = ObjectId.get().toString(),
    var name: String,
    var admin: String,
    var users: MutableList<String> = mutableListOf(),
    var groupColor: String = "#BDBDBD",

) {
    @Transient
    var messages: MutableList<Message> = mutableListOf()
}