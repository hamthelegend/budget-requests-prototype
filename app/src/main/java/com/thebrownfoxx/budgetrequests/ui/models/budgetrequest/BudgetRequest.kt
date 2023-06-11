package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest

import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.Signatories
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.models.user.officer.Officer

data class BudgetRequest(
    val id: Int,
    val title: String,
    val body: String,
    val expenses: List<Expense>,
    val requestingOrganization: Organization,
    val requestingOfficer: Officer,
    val signatories: Signatories,
) {
    val amount get() = expenses.sumOf { it.amount }
}