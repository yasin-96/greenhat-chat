package com.thm.greenhat.greenhatchat.model

data class ChangePasswordRequest(
    val oldPassInput: String,
    val newPass: String
)