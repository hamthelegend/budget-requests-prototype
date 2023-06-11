package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory

import com.thebrownfoxx.budgetrequests.ui.models.organization.OrganizationPosition
import com.thebrownfoxx.budgetrequests.ui.models.user.officer.Officer

data class OfficerSignatory(
    override val id: Int,
    override val user: Officer,
    val position: OrganizationPosition,
    override val hasSigned: Boolean,
) : Signatory() {
    override val role: String
        get() = position.text
}
