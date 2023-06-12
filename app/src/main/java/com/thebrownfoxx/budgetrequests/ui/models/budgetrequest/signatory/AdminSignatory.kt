package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory

import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin

data class AdminSignatory(
    override val id: Int,
    override val user: Admin,
    override val hasSigned: Boolean,
) : Signatory() {
    override fun signed() = copy(hasSigned = true)
    override fun unsigned() = copy(hasSigned = false)
}