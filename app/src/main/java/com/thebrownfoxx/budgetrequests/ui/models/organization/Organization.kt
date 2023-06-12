package com.thebrownfoxx.budgetrequests.ui.models.organization

import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.budgetrequests.data.randomOpaqueColor
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin

data class Organization(
    val id: Int? = null,
    val name: String,
    val adviser: Admin,
    val officers: OrganizationOfficers,
    val isStudentCouncil: Boolean,
    val profileBackground: Color = randomOpaqueColor,
)