package com.thebrownfoxx.budgetrequests.data.models.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thebrownfoxx.budgetrequests.data.hash.Hash

@Entity
data class UserEntity(
    @PrimaryKey val id: Int? = null,
    val type: UserType,
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val username: String,
    @Embedded val passwordHash: Hash,
    val profileBackground: ULong,
    val profilePictureFilename: String?,
    val signatureFilename: String?,
    val isSuperAdmin: Boolean,
)