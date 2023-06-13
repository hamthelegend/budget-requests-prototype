package com.thebrownfoxx.budgetrequests.ui.models.user.admin

import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.budgetrequests.data.getRandomOpaqueColor
import com.thebrownfoxx.budgetrequests.data.hash.Hash
import com.thebrownfoxx.budgetrequests.ui.models.user.User

data class Admin(
    override val id: Int? = null,
    override val firstName: String,
    override val middleName: String? = null,
    override val lastName: String,
    override val username: String,
    override val passwordHash: Hash,
    override val profileBackground: Color = getRandomOpaqueColor(),
    override val isPasswordPregenerated: Boolean = false,
    override val profilePictureFilename: String? = null,
    override val signatureFilename: String? = null,
    val isSuperAdmin: Boolean = false,
): User() {
    override fun copyWithId(id: Int) = copy(id = id)
}
