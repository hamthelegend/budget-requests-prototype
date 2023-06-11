package com.thebrownfoxx.budgetrequests.ui.models.user.admin

import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.budgetrequests.data.hash.Hash
import com.thebrownfoxx.budgetrequests.data.randomOpaqueColor
import com.thebrownfoxx.budgetrequests.ui.models.user.User

data class Admin(
    override val id: Int,
    override val firstName: String,
    override val middleName: String? = null,
    override val lastName: String,
    override val username: String,
    override val passwordHash: Hash,
    override val profileBackground: Color = randomOpaqueColor,
    override val profilePictureFilename: String? = null,
    override val signatureFilename: String? = null,
    val roles: List<AdminPosition> = listOf(),
): User()
