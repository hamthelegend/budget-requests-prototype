package ui

import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.DataSource
import com.thebrownfoxx.budgetrequests.ui.screens.budgetrequest.BudgetRequestScreen
import com.thebrownfoxx.budgetrequests.ui.screens.createrequest.CreateRequestScreen
import com.thebrownfoxx.budgetrequests.ui.screens.home.HomeScreen
import com.thebrownfoxx.budgetrequests.ui.screens.login.LoginScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Container() {
    var screen by remember { mutableStateOf(Screen.Login) }
    val density = LocalDensity.current

    var budgetRequest by remember { mutableStateOf(DataSource.budgetRequests.first()) }

    AnimatedContent(
        targetState = screen,
        transitionSpec = {
            slideIn(density = density) with slideOut(density = density)
        },
    ) { targetScreen ->
        when (targetScreen) {
            Screen.Login -> LoginScreen(onLogin = { screen = Screen.Home })
            Screen.Home -> HomeScreen(
                onLogout = { screen = Screen.Login },
                onCreateRequest = { screen = Screen.CreateRequest },
                onBudgetRequestClick = { screen = Screen.BudgetRequest }
            )
            Screen.CreateRequest -> CreateRequestScreen(onClose = { screen = Screen.Home })
            Screen.BudgetRequest -> BudgetRequestScreen(
                budgetRequest = budgetRequest,
                onBudgetRequestChange = { budgetRequest = it },
                onClose = { screen = Screen.Home },
            )
        }
    }
}

fun slideIn(density: Density, reverseDirection: Boolean = false) = slideInVertically {
    with(density) { (40 * if (reverseDirection) -1 else 1).dp.roundToPx() }
} + fadeIn()

fun slideOut(density: Density, reverseDirection: Boolean = false) = slideOutVertically {
    with(density) { (40 * if (reverseDirection) 1 else -1).dp.roundToPx() }
} + fadeOut()