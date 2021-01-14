package com.thm.greenhat.greenhatchat.utils

fun createAvatarName(email: String): String{
    var fn: String = ""
    var ln: String = ""
    val rawName = email.split("@")?.get(0);
    val sizeOfMail = rawName.split(".").size
    if (sizeOfMail == 2) {
        ln = rawName.split(".")?.get(1).toCharArray().firstOrNull()?.toUpperCase().toString()
    }
    fn = rawName.split(".")?.get(0).toCharArray().firstOrNull()?.toUpperCase().toString()

    println("${fn}${ln}")
    return "${fn}${ln}"
}