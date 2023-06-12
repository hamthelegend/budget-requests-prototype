package ui

import androidx.compose.animation.*
import androidx.compose.runtime.*
import com.thebrownfoxx.budgetrequests.data.DataSource
import com.thebrownfoxx.budgetrequests.ui.NavigationDirection
import com.thebrownfoxx.budgetrequests.ui.Navigator
import com.thebrownfoxx.budgetrequests.ui.screens.budgetrequest.BudgetRequestScreen
import com.thebrownfoxx.budgetrequests.ui.screens.createrequest.CreateRequestScreen
import com.thebrownfoxx.budgetrequests.ui.screens.home.HomeScreen
import com.thebrownfoxx.budgetrequests.ui.screens.login.LoginScreen
import com.thebrownfoxx.budgetrequests.ui.sharedZAxisEnter
import com.thebrownfoxx.budgetrequests.ui.sharedZAxisExit

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Container() {
    val navigator = remember { Navigator() }
    var budgetRequest by remember { mutableStateOf(DataSource.budgetRequests.first()) }

    AnimatedContent(
        targetState = navigator.screenStack.last(),
        transitionSpec = {
            val reverseDirection = navigator.direction == NavigationDirection.Backward
            sharedZAxisEnter(reverseDirection) with sharedZAxisExit(reverseDirection)
        },
    ) { targetScreen ->
        when (targetScreen) {
            Screen.Login -> LoginScreen(onLogin = { navigator.navigateTo(Screen.Home) })
            Screen.Home -> HomeScreen(
                onLogout = { navigator.popScreen() },
                onCreateRequest = { navigator.navigateTo(Screen.CreateRequest) },
                onBudgetRequestClick = { navigator.navigateTo(Screen.BudgetRequest) }
            )
            Screen.CreateRequest -> CreateRequestScreen(onClose = { navigator.popScreen() })
            Screen.BudgetRequest -> BudgetRequestScreen(
                budgetRequest = budgetRequest,
                onBudgetRequestChange = { budgetRequest = it },
                onClose = { navigator.popScreen() },
            )
        }
    }
}