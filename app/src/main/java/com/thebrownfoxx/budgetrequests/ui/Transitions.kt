package com.thebrownfoxx.budgetrequests.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

fun slideIn(density: Density, reverseDirection: Boolean = false) = slideInVertically {
    with(density) { (40 * if (reverseDirection) -1 else 1).dp.roundToPx() }
} + fadeIn()

fun slideOut(density: Density, reverseDirection: Boolean = false) = slideOutVertically {
    with(density) { (40 * if (reverseDirection) 1 else -1).dp.roundToPx() }
} + fadeOut()

@OptIn(ExperimentalAnimationApi::class)
fun sharedZAxisEnter(reverseDirection: Boolean = false) = if (!reverseDirection) scaleIn(
    initialScale = 0.8f,
    animationSpec = tween(durationMillis = 300),
) + fadeIn(
    initialAlpha = 0f,
    animationSpec = tween(durationMillis = 210, delayMillis = 90),
) else scaleIn(
    initialScale = 1.1f,
    animationSpec = tween(durationMillis = 300)
) + fadeIn(
    initialAlpha = 0f,
    animationSpec = tween(durationMillis = 90, easing = FastOutLinearInEasing)
)

@OptIn(ExperimentalAnimationApi::class)
fun sharedZAxisExit(reverseDirection: Boolean = false) = if (!reverseDirection) scaleOut(
    targetScale = 1.1f,
    animationSpec = tween(durationMillis = 300)
) + fadeOut(
    targetAlpha = 0f,
    animationSpec = tween(durationMillis = 90, easing = FastOutLinearInEasing)
) else scaleOut(
    targetScale = 0.8f,
    animationSpec = tween(durationMillis = 300),
) + fadeOut(
    targetAlpha = 0f,
    animationSpec = tween(durationMillis = 210, delayMillis = 90),
)