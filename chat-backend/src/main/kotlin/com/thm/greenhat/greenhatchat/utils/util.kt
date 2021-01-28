package com.thm.greenhat.greenhatchat.utils

/**
 *
 * @param email String
 * @return String
 */
fun createAvatarName(email: String): String {
    var fn: String = ""
    var ln: String = ""
    val rawName = email.split("@")?.get(0);
    val sizeOfMail = rawName.split(".").size
    if (sizeOfMail == 2) {
        ln = rawName.split(".")?.get(1).toCharArray().firstOrNull()?.toUpperCase().toString()
    }
    fn = rawName.split(".")?.get(0).toCharArray().firstOrNull()?.toUpperCase().toString()
    return "${fn}${ln}"
}