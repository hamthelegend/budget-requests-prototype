package com.thebrownfoxx.budgetrequests.data.models.organization

data class OrganizationOfficersEntity(
    val presidentId: Int,
    val vicePresidentId: Int,
    val secretaryId: Int,
    val treasurerId: Int,
    val auditorId: Int,
    val publicRelationsOfficerId: Int,
)