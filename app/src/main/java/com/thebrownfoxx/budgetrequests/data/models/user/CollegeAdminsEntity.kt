package com.thebrownfoxx.budgetrequests.data.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CollegeAdminsEntity(
    @PrimaryKey val id: Int? = null,
    val assistantDeanId: Int?,
    val deanId: Int?,
    val studentAffairsDirectorId: Int?,
)