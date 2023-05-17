package com.thebrownfoxx.budgetrequests.data

data class BudgetRequest(
    val title: String,
    val body: String,
    val organization: String,
    val amount: Double,
    val expenses: List<Expense>,
    val signatories: List<Signatory>,
)
