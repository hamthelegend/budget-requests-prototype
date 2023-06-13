package com.thebrownfoxx.budgetrequests.data

fun getRandomPassword(length: Int): String {
    val characterSet = ('0'..'9').toList() + ('a'..'z').toList() + ('A'..'Z').toList()
    val password = StringBuilder()

    for (i in 0 until length) {
        password.append(characterSet.random())
    }

    return password.toString()
}