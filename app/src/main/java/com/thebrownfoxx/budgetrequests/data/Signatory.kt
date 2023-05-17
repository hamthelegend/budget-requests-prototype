package com.thebrownfoxx.budgetrequests.data

data class Signatory(
    val name: String,
    val role: String,
    val hasSigned: Boolean,
    val hasReceivedRequest: Boolean,
)
