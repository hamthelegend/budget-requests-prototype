package com.thebrownfoxx.budgetrequests.ui.models.organization

import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin

data class Organization(
    val id: Int,
    val name: String,
    val adviser: Admin,
    val officers: OrganizationOfficers,
    val isStudentCouncil: Boolean,
)