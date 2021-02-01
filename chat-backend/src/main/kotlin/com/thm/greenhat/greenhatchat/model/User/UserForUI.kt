package com.thm.greenhat.greenhatchat.model.User

data class UserForUI(
    val id: String,
    val username: String,
    val email: String,
    val hasAvatarPicture: Boolean = false,
    val avatarName: String,
    val avatarPicture: String = ""
)