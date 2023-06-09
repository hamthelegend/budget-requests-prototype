package com.thebrownfoxx.budgetrequests.ui.models.user

import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.budgetrequests.data.getRandomColor
import com.thebrownfoxx.budgetrequests.data.hash.Hash

data class Officer(
    override val id: Int? = null,
    override val firstName: String,
    override val middleName: String? = null,
    override val lastName: String,
    override val username: String,
    override val passwordHash: Hash,
    override val isPasswordPregenerated: Boolean = false,
    override val profileBackground: Color = getRandomColor(),
    override val profilePictureFilename: String? = null,
    override val signatureFilename: String? = null,
): User() {
    override fun copyWithId(id: Int) = copy(id = id)
}
