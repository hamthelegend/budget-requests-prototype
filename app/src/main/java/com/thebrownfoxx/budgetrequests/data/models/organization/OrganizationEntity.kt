package com.thebrownfoxx.budgetrequests.data.models.organization

import androidx.compose.ui.graphics.Color
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrganizationEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val adviserId: Int,
    @Embedded val officers: OrganizationOfficersEntity,
    val isStudentCouncil: Boolean,
    val profileBackground: Color,
)