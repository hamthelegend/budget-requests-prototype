package com.thebrownfoxx.budgetrequests.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ui.Screen

class Navigator {
    val screenStack = mutableStateListOf(Screen.Login)
    var direction by mutableStateOf(NavigationDirection.Forward)

    fun navigateTo(screen: Screen) {
        direction = NavigationDirection.Forward
        screenStack.add(screen)
    }

    fun popScreen() {
        direction = NavigationDirection.Backward
        screenStack.removeLast()
    }
}

enum class NavigationDirection {
    Forward,
    Backward,
}