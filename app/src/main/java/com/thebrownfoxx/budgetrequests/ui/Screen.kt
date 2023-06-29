package com.thebrownfoxx.budgetrequests.ui

import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.models.user.Officer
import com.thebrownfoxx.budgetrequests.ui.models.user.User

sealed class Screen {
    object LoginScreen: Screen()
    object CreateSuperAdminScreen: Screen()
    class HomeScreen(val loggedInUser: User): Screen()
    class CreateRequestScreen(val loggedInOfficer: Officer): Screen()
    class BudgetRequestScreen(val budgetRequest: BudgetRequest, val loggedInUser: User): Screen()
    object AddUserScreen: Screen()
    class UserScreen(val user: User): Screen()
    object CollegeAdminsScreen: Screen()
    object UpdateCollegeAdminsScreen: Screen()
    object AddOrganizationScreen: Screen()
    class OrganizationScreen(val organization: Organization): Screen()
}