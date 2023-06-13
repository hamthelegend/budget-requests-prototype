package com.thebrownfoxx.budgetrequests.ui.models.user.officer

import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.budgetrequests.data.hash.Hash
import com.thebrownfoxx.budgetrequests.data.randomOpaqueColor
import com.thebrownfoxx.budgetrequests.ui.models.user.User

data class Officer(
    override val id: Int? = null,
    override val firstName: String,
    override val middleName: String? = null,
    override val lastName: String,
    override val username: String,
    override val passwordHash: Hash,
    override val profileBackground: Color = randomOpaqueColor,
    override val profilePictureFilename: String? = null,
    override val signatureFilename: String? = null,
): User() {
    override fun copyWithId(id: Int) = copy(id = id)
}
