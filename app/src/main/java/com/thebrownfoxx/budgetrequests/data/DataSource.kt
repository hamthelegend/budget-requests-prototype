package com.thebrownfoxx.budgetrequests.data

import com.thebrownfoxx.budgetrequests.data.hash.hash
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.Expense
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.AdminSignatory
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.OfficerSignatory
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.Signatories
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.models.organization.OrganizationOfficers
import com.thebrownfoxx.budgetrequests.ui.models.organization.OrganizationPosition
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.AdminPosition.Adviser
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.AdminPosition.AssistantDean
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.AdminPosition.Dean
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.AdminPosition.StudentAffairsDirector
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.AdminPosition.SuperAdmin
import com.thebrownfoxx.budgetrequests.ui.models.user.officer.Officer

object DataSource {
    val users = listOf<User>(
        Admin(
            id = 1,
            firstName = "Herb",
            lastName = "Ert",
            username = "herb_ert",
            passwordHash = hash("bowow"),
            roles = listOf(SuperAdmin, Adviser),
        ),
        Admin(
            id = 2,
            firstName = "Mario",
            lastName = "Io",
            username = "mar_io",
            passwordHash = hash("bowow"),
            roles = listOf(AssistantDean),
        ),
        Admin(
            id = 3,
            firstName = "Ru",
            lastName = "El",
            username = "ru_el",
            passwordHash = hash("bowow"),
            roles = listOf(Dean),
        ),
        Admin(
            id = 4,
            firstName = "Jus",
            lastName = "Tine",
            username = "jus_tine",
            passwordHash = hash("bowow"),
            roles = listOf(StudentAffairsDirector),
        ),
        Officer(
            id = 5,
            firstName = "Jer",
            lastName = "Icho",
            username = "jer_icho",
            passwordHash = hash("bowow"),
        ),
        Officer(
            id = 6,
            firstName = "Ma",
            lastName = "Rc",
            username = "ma_rc",
            passwordHash = hash("bowow"),
        ),
        Officer(
            id = 7,
            firstName = "Jon",
            lastName = "El",
            username = "jon_el",
            passwordHash = hash("bowow"),
        ),
        Officer(
            id = 8,
            firstName = "Bly",
            lastName = "The",
            username = "bly_the",
            passwordHash = hash("bowow"),
        ),
        Officer(
            id = 9,
            firstName = "Ham",
            lastName = "Uel",
            username = "ham_uel",
            passwordHash = hash("bowow"),
        ),
        Officer(
            id = 10,
            firstName = "Wes",
            lastName = "Tly",
            username = "wes_tly",
            passwordHash = hash("bowow"),
        ),
    )

    val organizations = listOf(
        Organization(
            id = 1,
            name = "Honor's Society",
            adviser = users.first { it.id == 1 } as Admin,
            officers = OrganizationOfficers(
                president = users.first { it.id == 5 } as Officer,
                vicePresident = users.first { it.id == 5 } as Officer,
                secretary = users.first { it.id == 5 } as Officer,
                treasurer = users.first { it.id == 5 } as Officer,
                auditor = users.first { it.id == 5 } as Officer,
                publicRelationsOfficer = users.first { it.id == 5 } as Officer,
            ),
            isStudentCouncil = false
        )
    )

    val budgetRequests = Array(10) { index ->
        val requestingOrganization = organizations.first()
        
        BudgetRequest(
            id = index,
            title = listOf("Furry Festival", "U-Week", "FuckCon").random(),
            body = """
                I am writing to request a budget allocation for Project [Project Name]. This project aims to [briefly describe the project's objectives and scope]. 
                In order to successfully execute this initiative, we require financial support to cover various expenses necessary for its implementation.
            """.trimIndent(),
            expenses = listOf(
                Expense("Water", 50000.00),
                Expense("Prize", 10000.00),
            ),
            requestingOrganization = requestingOrganization,
            requestingOfficer = requestingOrganization.officers.president,
            signatories = Signatories(
                treasurer = OfficerSignatory(
                    id = 1,
                    user = requestingOrganization.officers.treasurer,
                    position = OrganizationPosition.Treasurer,
                    hasSigned = false,
                ),
                auditor = OfficerSignatory(
                    id = 1,
                    user = requestingOrganization.officers.treasurer,
                    position = OrganizationPosition.Treasurer,
                    hasSigned = false,
                ),
                president = OfficerSignatory(
                    id = 1,
                    user = requestingOrganization.officers.treasurer,
                    position = OrganizationPosition.Treasurer,
                    hasSigned = false,
                ),
                adviser = AdminSignatory(
                    id = 1,
                    user = users.first { it is Admin && Adviser in it.roles } as Admin,
                    position = Adviser,
                    hasSigned = false,
                ),
                assistantDean = AdminSignatory(
                    id = 1,
                    user = users.first { it is Admin && AssistantDean in it.roles } as Admin,
                    position = AssistantDean,
                    hasSigned = false,
                ),
                dean = AdminSignatory(
                    id = 1,
                    user = users.first { it is Admin && Dean in it.roles } as Admin,
                    position = Dean,
                    hasSigned = false,
                ),
                studentAffairsDirector = AdminSignatory(
                    id = 1,
                    user = users.first { it is Admin && StudentAffairsDirector in it.roles } as Admin,
                    position = StudentAffairsDirector,
                    hasSigned = false,
                ),
            )
        )
    }.toList()
}