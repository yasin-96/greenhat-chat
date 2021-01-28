package com.thm.greenhat.greenhatchat.model

data class MessageToDisplay(
    var _id: String,
    var content: String,
    var created: String,
    var user: UserToDisplay,
    var groupId: String
)