package com.thebrownfoxx.budgetrequests.data.models.budgetrequest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseEntity(
    @PrimaryKey val id: Int? = null,
    val budgetRequestId: Int,
    val purpose: String,
    val amount: Double,
)