package com.thm.greenhat.greenhatchat.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class GroupResponse(
        @Id var _id: String = ObjectId.get().toString(),
        var name:String,
        var admin:String,
        var users:MutableList<UserToDisplay> = mutableListOf()
)
