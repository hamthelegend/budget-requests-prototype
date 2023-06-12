package com.thebrownfoxx.budgetrequests.data.models

import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.budgetrequests.data.models.user.UserEntity
import com.thebrownfoxx.budgetrequests.data.models.user.UserType
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.officer.Officer

fun UserEntity.toUser() = when (type) {
    UserType.Admin -> Admin(
        id = id,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        username = username,
        passwordHash = passwordHash,
        profileBackground = Color(profileBackground),
        profilePictureFilename = profilePictureFilename,
        signatureFilename = signatureFilename,
        isSuperAdmin = isSuperAdmin,
    )
    UserType.Officer -> Officer(
        id = id,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        username = username,
        passwordHash = passwordHash,
        profileBackground = Color(profileBackground),
        profilePictureFilename = profilePictureFilename,
        signatureFilename = signatureFilename,
    )
}

fun User.toUserEntity() = when (this) {

    is Admin -> UserEntity(
        id = id,
        type = UserType.Admin,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        username = username,
        passwordHash = passwordHash,
        profileBackground = profileBackground.value,
        profilePictureFilename = profilePictureFilename,
        signatureFilename = signatureFilename,
        isSuperAdmin = isSuperAdmin,
    )

    is Officer -> UserEntity(
        id = id,
        type = UserType.Officer,
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        username = username,
        passwordHash = passwordHash,
        profileBackground = profileBackground.value,
        profilePictureFilename = profilePictureFilename,
        signatureFilename = signatureFilename,
        isSuperAdmin = false,
    )

    else -> throw IllegalArgumentException()

}