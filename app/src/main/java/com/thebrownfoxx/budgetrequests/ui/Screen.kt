package com.thebrownfoxx.budgetrequests.ui

import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.models.user.User

sealed class Screen {
    object LoginScreen: Screen()
    object CreateSuperAdminScreen: Screen()
    class HomeScreen(val loggedInUser: User): Screen()
    object CreateRequestScreen: Screen()
    class BudgetRequestScreen(val budgetRequest: BudgetRequest): Screen()
}