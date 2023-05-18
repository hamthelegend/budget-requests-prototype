package com.thebrownfoxx.budgetrequests.ui.screens.home.budgetrequests

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.BudgetRequest
import com.thebrownfoxx.budgetrequests.data.formattedMonetaryAmount
import com.thebrownfoxx.budgetrequests.data.sampleBudgetRequest
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIcon
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun BudgetRequestCard(
    budgetRequest: BudgetRequest,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
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
                ProfileIcon(
                    text = budgetRequest.organization.first().toString(),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = budgetRequest.organization,
                    style = MaterialTheme.typography.titleSmall,
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
        BudgetRequestCard(sampleBudgetRequest)
    }
}