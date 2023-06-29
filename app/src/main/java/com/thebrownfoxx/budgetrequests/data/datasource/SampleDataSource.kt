package com.thebrownfoxx.budgetrequests.data.datasource

import com.thebrownfoxx.budgetrequests.data.hash.hash
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.Expense
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.Signatories
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.Signatory
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.models.organization.OrganizationOfficers
import com.thebrownfoxx.budgetrequests.ui.models.user.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.CollegeAdmins
import com.thebrownfoxx.budgetrequests.ui.models.user.Officer

object SampleDataSource {
    val herbErt = Admin(
        id = 1,
        firstName = "Herb",
        lastName = "Ert",
        username = "herb_ert",
        passwordHash = hash("bowow"),
        isSuperAdmin = true,
    )
    val marIo = Admin(
        id = 2,
        firstName = "Mar",
        lastName = "Io",
        username = "mar_io",
        passwordHash = hash("bowow"),
    )
    val ruEl = Admin(
        id = 3,
        firstName = "Ru",
        lastName = "El",
        username = "ru_el",
        passwordHash = hash("bowow"),
    )
    val jusTine = Admin(
        id = 4,
        firstName = "Jus",
        lastName = "Tine",
        username = "jus_tine",
        passwordHash = hash("bowow"),
    )
    val jerIcho = Officer(
        id = 5,
        firstName = "Jer",
        lastName = "Icho",
        username = "jer_icho",
        passwordHash = hash("bowow"),
    )
    val maRc = Officer(
        id = 6,
        firstName = "Ma",
        lastName = "Rc",
        username = "ma_rc",
        passwordHash = hash("bowow"),
    )
    val jonEl = Officer(
        id = 7,
        firstName = "Jon",
        lastName = "El",
        username = "jon_el",
        passwordHash = hash("bowow"),
    )
    val blyThe = Officer(
        id = 8,
        firstName = "Bly",
        lastName = "The",
        username = "bly_the",
        passwordHash = hash("bowow"),
    )
    val hamUel = Officer(
        id = 9,
        firstName = "Ham",
        lastName = "Uel",
        username = "ham_uel",
        passwordHash = hash("bowow"),
    )
    val wesTly = Officer(
        id = 10,
        firstName = "Wes",
        lastName = "Tly",
        username = "wes_tly",
        passwordHash = hash("bowow"),
    )

    val users = listOf(
        herbErt,
        marIo,
        ruEl,
        jusTine,
        jerIcho,
        maRc,
        jonEl,
        blyThe,
        hamUel,
        wesTly,
    )

    val collegeAdmins = CollegeAdmins(
        assistantDean = ruEl,
        dean = marIo,
        studentAffairsDirector = jusTine,
    )

    val honorsSociety = Organization(
        id = 1,
        name = "Honors' Society",
        adviser = herbErt,
        officers = OrganizationOfficers(
            president = jerIcho,
            vicePresident = maRc,
            secretary = jonEl,
            treasurer = blyThe,
            auditor = hamUel,
            publicRelationsOfficer = wesTly,
        ),
    )
    val idiotsSociety = Organization(
        id = 1,
        name = "Furries Society",
        adviser = marIo,
        officers = OrganizationOfficers(
            president = maRc,
            vicePresident = jonEl,
            secretary = hamUel,
            treasurer = blyThe,
            auditor = jerIcho,
            publicRelationsOfficer = wesTly,
        ),
    )

    val organizations = listOf(honorsSociety)

    val budgetRequests = Array(10) { index ->
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
            requestingOrganization = honorsSociety,
            requestingOfficer = honorsSociety.officers.president,
            signatories = Signatories(
                treasurer = Signatory(
                    user = honorsSociety.officers.treasurer,
                    hasSigned = false,
                ),
                auditor = Signatory(
                    user = honorsSociety.officers.auditor,
                    hasSigned = false,
                ),
                president = Signatory(
                    user = honorsSociety.officers.president,
                    hasSigned = false,
                ),
                adviser = Signatory(
                    user = honorsSociety.adviser,
                    hasSigned = false,
                ),
                assistantDean = Signatory(
                    user = collegeAdmins.assistantDean!!,
                    hasSigned = false,
                ),
                dean = Signatory(
                    user = collegeAdmins.dean!!,
                    hasSigned = false,
                ),
                studentAffairsDirector = Signatory(
                    user = collegeAdmins.studentAffairsDirector!!,
                    hasSigned = false,
                ),
            )
        )
    }.toList()
}