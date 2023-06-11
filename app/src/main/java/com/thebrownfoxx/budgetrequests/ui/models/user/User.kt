package com.thebrownfoxx.budgetrequests.ui.models.user

import androidx.compose.ui.graphics.Color
import com.thebrownfoxx.budgetrequests.data.hash.Hash

abstract class User {
    abstract val id: Int
    abstract val firstName: String
    abstract val middleName: String?
    abstract val lastName: String
    abstract val username: String
    abstract val passwordHash: Hash
    abstract val profileBackground: Color
    abstract val profilePictureFilename: String?
    abstract val signatureFilename: String?

    val fullName: String
        get() {
            var fullName = firstName
            middleName?.let { middleName ->
                val middleNameWords = middleName.split(' ')
                fullName += ' '
                middleNameWords.forEach { word ->
                    fullName += "${word.first()}."
                }
            }
            fullName += " $lastName"
            return fullName
        }
}