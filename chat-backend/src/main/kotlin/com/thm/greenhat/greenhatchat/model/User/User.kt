package com.thm.greenhat.greenhatchat.model.User

import com.thm.greenhat.greenhatchat.utils.createAvatarName
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
data class User(
    @Id var id: String = ObjectId.get().toString(),
    var username: String,
    var password: String,
    var email: String,
    var hasAvatarPicture: Boolean = false,
    var avatarName: String = createAvatarName(email),
    var avatarPicture: String = ""
)