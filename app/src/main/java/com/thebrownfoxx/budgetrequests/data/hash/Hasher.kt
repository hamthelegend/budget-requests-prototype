package com.thebrownfoxx.budgetrequests.data.hash

import java.security.MessageDigest
import java.util.Base64
import kotlin.random.Random

fun hash(plaintext: String, saltText: String? = null): Hash {
    val sha512 = MessageDigest.getInstance("SHA-512")
    val salt = when (saltText) {
        null -> Random.Default.nextBytes(16)
        else -> Base64.getDecoder().decode(saltText)
    }
    val hash = sha512.digest(plaintext.toByteArray() + salt)
    return Hash(
        value = Base64.getEncoder().encodeToString(hash),
        salt = Base64.getEncoder().encodeToString(salt),
    )
}