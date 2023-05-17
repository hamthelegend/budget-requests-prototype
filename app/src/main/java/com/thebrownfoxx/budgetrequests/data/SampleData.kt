package com.thebrownfoxx.budgetrequests.data

import kotlin.math.roundToInt
import kotlin.random.Random

private fun generatedRandomMonetaryAmount() =
    (Random.nextDouble(1_000.00, 1_000_000.00) * 100.0).roundToInt().toDouble() / 100.0

val sampleBudgetRequest =
    BudgetRequest(
        title = "U-fucking-week",
        body = """
                    The Institute of Electronics Engineers of the Philippines – AUF Student Chapter has organized
                    a Webinar this School Year and Outgoing Ceremony to be held on April 30, 2022. Additionally,
                    an amount of ₱1,000 will also be collected by the parent organization with regards to the
                    Affiliation fee for Higher Education Institution affiliated with the Institute of Electronics
                    Engineers of the Philippines (IECEP). In line with this, the Institute of Electronics Engineers
                    of the Philippines – AUF Student Chapter Officers would like to humbly request for the
                    release of budget from the College of Engineering and Architecture Funds amounting to Five
                    Thousand Pesos (PhP 5,000.00).
                """.trimIndent().replace('\n', ' '),
        organization = "Jericho's Cult",
        amount = generatedRandomMonetaryAmount(),
    )

val sampleBudgetRequests = Array(20) {
    sampleBudgetRequest.copy(
        amount = generatedRandomMonetaryAmount(),
    )
}.toList()
