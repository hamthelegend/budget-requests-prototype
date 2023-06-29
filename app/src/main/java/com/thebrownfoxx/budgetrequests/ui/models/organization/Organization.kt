package com.thebrownfoxx.budgetrequests.ui.models.organization

import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.budgetrequests.data.getRandomColor
import com.thebrownfoxx.budgetrequests.ui.models.user.Admin

data class Organization(
    val id: Int? = null,
    val name: String,
    val adviser: Admin,
    val officers: OrganizationOfficers,
    val profileBackground: Color = getRandomColor(),
)