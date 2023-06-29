package com.thebrownfoxx.budgetrequests.ui.screens.home.budgetrequests

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.datasource.SampleDataSource
import com.thebrownfoxx.budgetrequests.data.formattedMonetaryAmount
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.shared.Profile
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetRequestCard(
    budgetRequest: BudgetRequest,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Column(modifier = Modifier.padding(32.dp)) {
            Text(
                text = budgetRequest.title,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = budgetRequest.body,
                maxLines = 3,
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Profile(
                    name = budgetRequest.requestingOrganization.name,
                    background = budgetRequest.requestingOrganization.profileBackground,
                )
                Spacer(modifier = Modifier.weight(1f))
                InfoChip(text = budgetRequest.amount.formattedMonetaryAmount)
            }
        }
    }
}

@Preview
@Composable
fun BudgetRequestCardPreview() {
    BudgetRequestsTheme {
        BudgetRequestCard(budgetRequest = SampleDataSource.budgetRequests.first(), onClick = {})
    }
}