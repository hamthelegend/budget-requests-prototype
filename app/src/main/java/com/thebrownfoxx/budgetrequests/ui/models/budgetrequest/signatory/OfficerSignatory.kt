package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory

import com.thebrownfoxx.budgetrequests.ui.models.user.officer.Officer

data class OfficerSignatory(
    override val id: Int,
    override val user: Officer,
    override val hasSigned: Boolean,
) : Signatory() {
    override fun signed() = copy(hasSigned = true)
    override fun unsigned() = copy(hasSigned = false)
}
