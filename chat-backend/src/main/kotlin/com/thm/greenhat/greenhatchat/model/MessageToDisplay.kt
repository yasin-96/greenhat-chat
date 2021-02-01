package com.thm.greenhat.greenhatchat.model

import com.thm.greenhat.greenhatchat.model.User.UserToDisplay

data class MessageToDisplay(
    var _id: String,
    var content: String,
    var created: String,
    var user: UserToDisplay,
    var groupId: String
)