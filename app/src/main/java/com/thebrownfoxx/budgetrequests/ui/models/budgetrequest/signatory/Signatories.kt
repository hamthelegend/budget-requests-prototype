package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory

import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.SigningStage.Approved
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.SigningStage.Deans
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.SigningStage.Organization
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.SigningStage.StudentAffairsDirector
import com.thebrownfoxx.budgetrequests.ui.models.organization.OrganizationPosition
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.AdminPosition

data class Signatories(
    val treasurer: OfficerSignatory,
    val auditor: OfficerSignatory,
    val president: OfficerSignatory,
    val adviser: AdminSignatory,
    val assistantDean: AdminSignatory,
    val dean: AdminSignatory,
    val studentAffairsDirector: AdminSignatory,
) {

    fun toList() = listOf(
        treasurer,
        auditor,
        president,
        adviser,
        assistantDean,
        dean,
        studentAffairsDirector,
    )

    fun getSigningStage() = when {

        toList().all { it.hasSigned } -> Approved

        listOf(dean, assistantDean, adviser, president, auditor, treasurer).all { it.hasSigned } ->
            StudentAffairsDirector

        listOf(adviser, president, auditor, treasurer).all { it.hasSigned } -> Deans

        else -> Organization

    }

}

fun List<Signatory>.toSignatories(adviser: Admin) =
    Signatories(
        treasurer = first { it is OfficerSignatory && it.position == OrganizationPosition.Treasurer } as OfficerSignatory,
        auditor = first { it is OfficerSignatory && it.position == OrganizationPosition.Auditor } as OfficerSignatory,
        president = first { it is OfficerSignatory && it.position == OrganizationPosition.President } as OfficerSignatory,
        adviser = first { it is AdminSignatory && it.user == adviser } as AdminSignatory,
        assistantDean = first { it is AdminSignatory && it.position == AdminPosition.AssistantDean } as AdminSignatory,
        dean = first { it is AdminSignatory && it.position == AdminPosition.Dean } as AdminSignatory,
        studentAffairsDirector = first { it is AdminSignatory && it.position == AdminPosition.StudentAffairsDirector } as AdminSignatory,
    )