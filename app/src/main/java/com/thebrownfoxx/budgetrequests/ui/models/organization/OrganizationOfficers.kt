package com.thebrownfoxx.budgetrequests.ui.models.organization

import com.thebrownfoxx.budgetrequests.ui.models.user.officer.Officer

data class OrganizationOfficers(
    val president: Officer,
    val vicePresident: Officer,
    val secretary: Officer,
    val treasurer: Officer,
    val auditor: Officer,
    val publicRelationsOfficer: Officer,
)