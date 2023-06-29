package com.thebrownfoxx.budgetrequests.ui.models.organization

import com.thebrownfoxx.budgetrequests.ui.models.user.Officer

data class OrganizationOfficers(
    val president: Officer,
    val vicePresident: Officer,
    val secretary: Officer,
    val treasurer: Officer,
    val auditor: Officer,
    val publicRelationsOfficer: Officer,
) {
    fun toList() = listOf(
        president,
        vicePresident,
        secretary,
        treasurer,
        auditor,
        publicRelationsOfficer,
    )
    fun toMap() = mapOf(
        OrganizationPosition.President to president,
        OrganizationPosition.VicePresident to vicePresident,
        OrganizationPosition.Secretary to secretary,
        OrganizationPosition.Treasurer to treasurer,
        OrganizationPosition.Auditor to auditor,
        OrganizationPosition.PublicRelationsOfficer to publicRelationsOfficer,
    )
}