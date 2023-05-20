package com.thebrownfoxx.budgetrequests.data

data class BudgetRequest(
    val title: String,
    val body: String,
    val requester: String,
    val author: String,
    val expenses: List<Expense>,
    val signatories: List<Signatory>,
) {
    val amount = expenses.sumOf { it.amount }
}