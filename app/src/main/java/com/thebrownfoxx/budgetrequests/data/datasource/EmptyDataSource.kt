package com.thebrownfoxx.budgetrequests.data.datasource

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.Signatories
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.models.user.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.CollegeAdmins
import com.thebrownfoxx.budgetrequests.ui.models.user.Officer
import com.thebrownfoxx.budgetrequests.ui.models.user.User

object EmptyDataSource {
    var users by mutableStateOf(listOf<User>())
        private set

    var collegeAdmins by mutableStateOf(
        CollegeAdmins(
            assistantDean = null,
            dean = null,
            studentAffairsDirector = null,
        )
    )
        private set

    var organizations by mutableStateOf(listOf<Organization>())
        private set

    var budgetRequests by mutableStateOf(listOf<BudgetRequest>())
        private set


    private var lastUserId = 0
    private var lastOrganizationId = 0
    private var lastBudgetRequestId = 0

    val hasSuperAdmin get() = users.any { user -> user is Admin && user.isSuperAdmin }

    fun getUser(username: String) = users.firstOrNull { user -> user.username == username }

    fun getAdmins() = users.filterIsInstance<Admin>()

    fun getOfficers() = users.filterIsInstance<Officer>()

    fun addUser(user: User) {
        val newUser = user.copyWithId(id = lastUserId++)
        users = users + newUser
    }

    fun updateCollegeAdmins(newCollegeAdmins: CollegeAdmins) {
        collegeAdmins = newCollegeAdmins
    }

    fun getOrganizations(officer: Officer) = organizations.filter { organization ->
        officer in organization.officers.toList()
    }

    fun addOrganization(organization: Organization) {
        val newOrganization = organization.copy(id = lastOrganizationId++)
        organizations = organizations + newOrganization
    }

    fun addBudgetRequest(budgetRequest: BudgetRequest) {
        val newBudgetRequest = budgetRequest.copy(id = lastBudgetRequestId++)
        budgetRequests = budgetRequests + newBudgetRequest
    }

    fun updateBudgetRequest(budgetRequest: BudgetRequest) {
        val newBudgetRequests = budgetRequests.toMutableList()
        val oldBudgetRequest = budgetRequests.first { it.id == budgetRequest.id }
        val index = budgetRequests.indexOf(oldBudgetRequest)
        newBudgetRequests[index] = budgetRequest
        budgetRequests = newBudgetRequests
    }

    fun getDefaultSignatories(organization: Organization) = Signatories(
        treasurer = organization.officers.treasurer,
        auditor = organization.officers.auditor,
        president = organization.officers.president,
        adviser = organization.adviser,
        assistantDean = collegeAdmins.assistantDean!!,
        dean = collegeAdmins.dean!!,
        studentAffairsDirector = collegeAdmins.studentAffairsDirector!!,
    )
}