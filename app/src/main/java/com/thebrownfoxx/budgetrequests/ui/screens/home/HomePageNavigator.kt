package com.thebrownfoxx.budgetrequests.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar.sideBarOptions

object HomePageNavigator {
    var option by mutableStateOf(sideBarOptions.first())
}