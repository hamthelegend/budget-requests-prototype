package com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory


data class Signatories(
    val treasurer: Signatory,
    val auditor: Signatory,
    val president: Signatory,
    val adviser: Signatory,
    val assistantDean: Signatory,
    val dean: Signatory,
    val studentAffairsDirector: Signatory,
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
    treasurer = get(SignatoryPosition.Treasurer)!!,
    auditor = get(SignatoryPosition.Auditor)!!,
    president = get(SignatoryPosition.President)!!,
    adviser = get(SignatoryPosition.Adviser)!!,
    assistantDean = get(SignatoryPosition.AssistantDean)!!,
    dean = get(SignatoryPosition.Dean)!!,
    studentAffairsDirector = get(SignatoryPosition.AssistantDean)!!,
)