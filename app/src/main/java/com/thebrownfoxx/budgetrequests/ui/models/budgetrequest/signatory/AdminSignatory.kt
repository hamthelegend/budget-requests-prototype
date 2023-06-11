package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory

import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.AdminPosition

data class AdminSignatory(
    override val id: Int,
    override val user: Admin,
    val position: AdminPosition,
    override val hasSigned: Boolean,
) : Signatory() {
    override val role: String
        get() = position.text
}
