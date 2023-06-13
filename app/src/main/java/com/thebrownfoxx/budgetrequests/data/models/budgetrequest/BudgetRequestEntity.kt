package com.thebrownfoxx.budgetrequests.data.models.budgetrequest

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thebrownfoxx.budgetrequests.data.models.budgetrequest.signatory.SignatoriesEntity

@Entity
data class BudgetRequestEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val body: String,
    val requestingOrganizationId: Int,
    val requestingOfficerId: Int,
    val signatories: SignatoriesEntity,
)