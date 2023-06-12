package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory

import com.thebrownfoxx.budgetrequests.ui.models.user.User

data class Signatory(
    val id: Int? = null,
    val user: User,
    val hasSigned: Boolean,
) {
    fun signed() = copy(hasSigned = true)
    fun unsigned() = copy(hasSigned = false)
}