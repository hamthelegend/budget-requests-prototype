package com.thebrownfoxx.budgetrequests.data

import androidx.compose.ui.graphics.Color
import kotlin.random.Random
import kotlin.random.nextLong

fun getRandomOpaqueColor(): Color = Color(Random.nextLong(0x64000000.toLong()..0x64FFFFFF))