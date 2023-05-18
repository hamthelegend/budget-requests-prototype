package com.thebrownfoxx.budgetrequests.data

import java.time.Year

data class BudgetRequest(
    val title: String,
    val body: String,
    val organization: String,
    val expenses: List<Expense>,
    val signatories: List<Signatory>,
) {
    val amount = expenses.sumOf { it.amount }
}