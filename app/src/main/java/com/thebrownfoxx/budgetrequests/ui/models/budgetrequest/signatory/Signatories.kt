package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory



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

    fun toMap() = mapOf(
        SignatoryPosition.Treasurer to treasurer,
        SignatoryPosition.Auditor to auditor,
        SignatoryPosition.President to president,
        SignatoryPosition.Adviser to adviser,
        SignatoryPosition.AssistantDean to assistantDean,
        SignatoryPosition.Dean to dean,
        SignatoryPosition.StudentAffairsDirector to studentAffairsDirector,
    )

    fun getSigningStage() = when {

        toList().all { it.hasSigned } -> SigningStage.Approved

        listOf(dean, assistantDean, adviser, president, auditor, treasurer).all { it.hasSigned } ->
            SigningStage.StudentAffairsDirector

        listOf(adviser, president, auditor, treasurer).all { it.hasSigned } -> SigningStage.Deans

        else -> SigningStage.Organization

    }

    fun getSignatoryPosition(signatory: Signatory) = when (signatory) {
        treasurer -> SignatoryPosition.Treasurer
        auditor -> SignatoryPosition.Auditor
        president -> SignatoryPosition.President
        adviser -> SignatoryPosition.Adviser
        assistantDean -> SignatoryPosition.AssistantDean
        dean -> SignatoryPosition.Dean
        studentAffairsDirector -> SignatoryPosition.StudentAffairsDirector
        else -> throw IllegalArgumentException("$signatory is not a signatory of this budget request")
    }
}

fun Map<SignatoryPosition, Signatory>.toSignatories() = Signatories(
    treasurer = get(SignatoryPosition.Treasurer) as OfficerSignatory,
    auditor = get(SignatoryPosition.Auditor) as OfficerSignatory,
    president = get(SignatoryPosition.President) as OfficerSignatory,
    adviser = get(SignatoryPosition.Adviser) as AdminSignatory,
    assistantDean = get(SignatoryPosition.AssistantDean) as AdminSignatory,
    dean = get(SignatoryPosition.Dean) as AdminSignatory,
    studentAffairsDirector = get(SignatoryPosition.AssistantDean) as AdminSignatory,
)