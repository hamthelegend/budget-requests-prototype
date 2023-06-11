package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory

import com.thebrownfoxx.budgetrequests.ui.models.user.User

abstract class Signatory {
    abstract val id: Int
    abstract val user: User
    abstract val role: String
    abstract val hasSigned: Boolean
}