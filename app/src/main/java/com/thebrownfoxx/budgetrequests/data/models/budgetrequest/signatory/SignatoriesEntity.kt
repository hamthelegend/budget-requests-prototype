package com.thebrownfoxx.budgetrequests.data.models.budgetrequest.signatory

import androidx.room.Embedded

data class SignatoriesEntity(
    @Embedded val treasurer: SignatoryEntity,
    @Embedded val auditor: SignatoryEntity,
    @Embedded val president: SignatoryEntity,
    @Embedded val adviser: SignatoryEntity,
    @Embedded val assistantDean: SignatoryEntity,
    @Embedded val dean: SignatoryEntity,
    @Embedded val studentAffairsDirector: SignatoryEntity,
)