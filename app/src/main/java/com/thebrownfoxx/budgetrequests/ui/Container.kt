package com.thebrownfoxx.budgetrequests.ui

import androidx.compose.animation.*
import androidx.compose.runtime.*
import com.thebrownfoxx.budgetrequests.data.EmptyDataSource
import com.thebrownfoxx.budgetrequests.ui.screens.budgetrequest.BudgetRequestScreen
import com.thebrownfoxx.budgetrequests.ui.screens.createrequest.CreateRequestScreen
import com.thebrownfoxx.budgetrequests.ui.screens.createsuperadmin.CreateSuperAdminScreen
import com.thebrownfoxx.budgetrequests.ui.screens.home.HomePageNavigator
import com.thebrownfoxx.budgetrequests.ui.screens.home.HomeScreen
import com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar.sideBarOptions
import com.thebrownfoxx.budgetrequests.ui.screens.login.LoginScreen
import com.thebrownfoxx.budgetrequests.ui.screens.user.UserScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Container() {
    val dataSource = EmptyDataSource
    val navigator = remember { Navigator(dataSource.hasSuperAdmin) }
    val screen = navigator.screenStack.last()

    AnimatedContent(
        targetState = screen,
        transitionSpec = {
            val reverseDirection = navigator.direction == NavigationDirection.Backward
            sharedZAxisEnter(reverseDirection) with sharedZAxisExit(reverseDirection)
        },
    ) { targetScreen ->
        when (targetScreen) {
            Screen.LoginScreen -> LoginScreen(
                onLogin = { user ->
                    navigator.navigateTo(Screen.HomeScreen(user))
                }
            )

            Screen.CreateSuperAdminScreen -> CreateSuperAdminScreen(
                onCreateSuperAdmin = { superAdmin ->
                    dataSource.addUser(superAdmin)
                    navigator.popScreen()
                    navigator.navigateTo(Screen.HomeScreen(superAdmin))
                }
            )

            is Screen.HomeScreen -> HomeScreen(
                loggedInUser = targetScreen.loggedInUser,
                onLogout = {
                    navigator.popScreen()
                    HomePageNavigator.option = sideBarOptions.first()
                },
                onNavigateToCreateRequest = { navigator.navigateTo(Screen.CreateRequestScreen) },
                onBudgetRequestClick = { navigator.navigateTo(Screen.BudgetRequestScreen(it)) },
                onUserClick = { navigator.navigateTo(Screen.UserScreen(it)) },
                onNavigateToAddUser = { navigator.navigateTo(Screen.AddUserScreen) }
            )

            Screen.CreateRequestScreen -> CreateRequestScreen(
                onClose = { navigator.popScreen() },
                onAddBudgetRequest = {},
            )

            is Screen.BudgetRequestScreen -> BudgetRequestScreen(
                budgetRequest = targetScreen.budgetRequest,
                onBudgetRequestChange = { dataSource.updateBudgetRequest(it) },
                onClose = { navigator.popScreen() },
            )

            Screen.AddUserScreen -> AddUserScreen(
                onAddUser = { user ->
                    dataSource.addUser(user)
                    navigator.popScreen()
                },
                onClose = { navigator.popScreen() }
            )

            is Screen.UserScreen -> UserScreen(
                user = targetScreen.user,
                onClose = { navigator.popScreen() }
            )
        }
    }
}