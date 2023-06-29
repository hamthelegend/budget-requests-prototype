package com.thebrownfoxx.budgetrequests.ui

import androidx.compose.animation.*
import androidx.compose.runtime.*
import com.thebrownfoxx.budgetrequests.data.datasource.EmptyDataSource
import com.thebrownfoxx.budgetrequests.ui.models.updatecollegeadmins.UpdateCollegeAdminsScreen
import com.thebrownfoxx.budgetrequests.ui.screens.addorganization.AddOrganizationScreen
import com.thebrownfoxx.budgetrequests.ui.screens.adduser.AddUserScreen
import com.thebrownfoxx.budgetrequests.ui.screens.budgetrequest.BudgetRequestScreen
import com.thebrownfoxx.budgetrequests.ui.screens.collegeadmins.CollegeAdminsScreen
import com.thebrownfoxx.budgetrequests.ui.screens.createrequest.CreateRequestScreen
import com.thebrownfoxx.budgetrequests.ui.screens.createsuperadmin.CreateSuperAdminScreen
import com.thebrownfoxx.budgetrequests.ui.screens.home.HomePageNavigator
import com.thebrownfoxx.budgetrequests.ui.screens.home.HomeScreen
import com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar.sideBarOptions
import com.thebrownfoxx.budgetrequests.ui.screens.login.LoginScreen
import com.thebrownfoxx.budgetrequests.ui.screens.organization.OrganizationScreen
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
                onNavigateToCreateRequest = { navigator.navigateTo(Screen.CreateRequestScreen(it)) },
                onBudgetRequestClick = { budgetRequest, loggedInUser ->  navigator.navigateTo(Screen.BudgetRequestScreen(budgetRequest, loggedInUser)) },
                onNavigateToAddUser = { navigator.navigateTo(Screen.AddUserScreen) },
                onUserClick = { navigator.navigateTo(Screen.UserScreen(it)) },
                onNavigateToAddOrganization = { navigator.navigateTo(Screen.AddOrganizationScreen )},
                onOrganizationClick = { navigator.navigateTo(Screen.OrganizationScreen(it)) },
                onNavigateToCollegeAdmins = { navigator.navigateTo(Screen.CollegeAdminsScreen) }
            )

            is Screen.CreateRequestScreen -> CreateRequestScreen(
                loggedInOfficer = targetScreen.loggedInOfficer,
                onClose = { navigator.popScreen() },
                onAddBudgetRequest = { dataSource.addBudgetRequest(it) },
            )

            is Screen.BudgetRequestScreen -> BudgetRequestScreen(
                budgetRequest = targetScreen.budgetRequest,
                loggedInUser = targetScreen.loggedInUser,
                onBudgetRequestChange = { dataSource.updateBudgetRequest(it) },
                onClose = { navigator.popScreen() },
            )

            Screen.AddUserScreen -> AddUserScreen(
                onAddUser = { user -> dataSource.addUser(user) },
                onClose = { navigator.popScreen() }
            )

            is Screen.UserScreen -> UserScreen(
                user = targetScreen.user,
                onClose = { navigator.popScreen() }
            )

            Screen.CollegeAdminsScreen -> CollegeAdminsScreen(
                collegeAdmins = dataSource.collegeAdmins,
                onNavigateToEditCollegeAdmins = { navigator.navigateTo(Screen.UpdateCollegeAdminsScreen) },
                onClose = { navigator.popScreen() }
            )

            Screen.UpdateCollegeAdminsScreen -> UpdateCollegeAdminsScreen (
                onClose = { navigator.popScreen() }
            )

            Screen.AddOrganizationScreen -> AddOrganizationScreen(
                onAddOrganization = { organization -> dataSource.addOrganization(organization) },
                onClose = { navigator.popScreen() }
            )

            is Screen.OrganizationScreen -> OrganizationScreen(
                organization = targetScreen.organization,
                onClose = { navigator.popScreen() }
            )
        }
    }
}