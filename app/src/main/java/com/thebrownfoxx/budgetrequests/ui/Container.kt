package ui

import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.ui.screens.home.HomeScreen
import com.thebrownfoxx.budgetrequests.ui.screens.login.LoginScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Container() {
    var lastScreen by remember { mutableStateOf<Screen?>(null) }
    var screen by remember { mutableStateOf(Screen.Login) }
    val density = LocalDensity.current

    AnimatedContent(
        targetState = screen,
        transitionSpec = {
            slideIn(density = density) with slideOut(density = density)
        },
    ) {
        when (it) {
            Screen.Login -> LoginScreen(onLogin = { screen = Screen.Home })
            Screen.Home -> HomeScreen(onLogout = { screen = Screen.Login })
        }
    }
}

fun slideIn(density: Density, reverseDirection: Boolean = false) = slideInVertically {
    with(density) { (40 * if (reverseDirection) -1 else 1).dp.roundToPx() }
} + fadeIn()

fun slideOut(density: Density, reverseDirection: Boolean = false) = slideOutVertically {
    with(density) { (40 * if (reverseDirection) 1 else -1).dp.roundToPx() }
} + fadeOut()