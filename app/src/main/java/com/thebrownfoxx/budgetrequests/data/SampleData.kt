package com.thebrownfoxx.budgetrequests.data

import java.text.NumberFormat
import kotlin.math.roundToInt
import kotlin.random.Random

val Double.formattedMonetaryAmount: String
    get() = "₱ ${NumberFormat.getNumberInstance().format(this)}"

fun generateRandomMonetaryAmount() =
    (Random.nextDouble(1_000.00, 1_000_000.00) * 100.0).roundToInt().toDouble() / 100.0

val sampleExpense
    get() = Expense(
        purpose = listOf("Winner Prize", "Bidet Fee", "Corruption").random(),
        amount = generateRandomMonetaryAmount(),
    )

val sampleBudgetRequest
    get() = BudgetRequest(
        title = listOf("U-fucking-week", "Furry Festival").random(),
        body =
        """
                    The Institute of Electronics Engineers of the Philippines – AUF Student Chapter has organized
                    a Webinar this School Year and Outgoing Ceremony to be held on April 30, 2022. Additionally,
                    an amount of ₱1,000 will also be collected by the parent organization with regards to the
                    Affiliation fee for Higher Education Institution affiliated with the Institute of Electronics
                    Engineers of the Philippines (IECEP). In line with this, the Institute of Electronics Engineers
                    of the Philippines – AUF Student Chapter Officers would like to humbly request for the
                    release of budget from the College of Engineering and Architecture Funds amounting to Five
                    Thousand Pesos (PhP 5,000.00).    
                """.trimIndent().trimIndent().replace('\n', ' '),
        requester = sampleOrganizations.random(),
        expenses = Array(3) { sampleExpense }.toList(),
        author = listOf("Jericho Diaz", "Justine Manalansan", "Brian").random(),
        signatories = listOf(
            Signatory(
                name = "Herb Ert",
                role = "Follower of the Above",
                hasSigned = true,
                hasReceivedRequest = true,
            ),
            Signatory(
                name = "Mar Io",
                role = "The Kind Above",
                hasSigned = false,
                hasReceivedRequest = true,
            ),
            Signatory(
                name = "Ru El",
                role = "The Above!!!",
                hasSigned = false,
                hasReceivedRequest = true,
            ),
            Signatory(
                name = "Jus Tine",
                role = "Ruler of them all",
                hasSigned = false,
                hasReceivedRequest = false,
            ),
        ),
    )

val sampleBudgetRequests get() = Array(20) { sampleBudgetRequest }.toList()

val sampleOrganizations = listOf("ICPEP", "IECEP", "Jericho's Cult")