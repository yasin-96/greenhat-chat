package com.thm.greenhat.greenhatchat.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("user")
data class User(
        @Id var id: UUID?,
        var username:String,
        var password:String,
        var email:String,
        var groups:List<UUID>,
        var blockedUsers:List<UUID>
)