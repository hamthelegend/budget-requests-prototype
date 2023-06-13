package com.thebrownfoxx.budgetrequests.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Navigator(hasSuperAdmin: Boolean) {
    val screenStack = mutableStateListOf<Screen>(Screen.LoginScreen)
    var direction by mutableStateOf(NavigationDirection.Forward)

    init {
        if (!hasSuperAdmin) {
            navigateTo(Screen.CreateSuperAdminScreen)
        }
    }

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