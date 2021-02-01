package com.thm.greenhat.greenhatchat.model.Group

data class GroupUserUpdate(
    val groupId: String,
    val userId: String? = null,
    val userIds: MutableList<String>? = null
)