package com.thebrownfoxx.budgetrequests.ui.screens.budgetrequest

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.DataSource
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.screens.createrequest.ExpenseChip
import com.thebrownfoxx.budgetrequests.ui.shared.Profile
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetRequestDetails(
    budgetRequest: BudgetRequest,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .widthIn(max = 512.dp)
    ) {
        OutlinedTextField(
            value = budgetRequest.body,
            onValueChange = { },
            label = { Text(text = "Body") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column(modifier = Modifier.animateContentSize()) {
            budgetRequest.expenses.forEach { expense ->
                Spacer(modifier = Modifier.height(4.dp))
                ExpenseChip(
                    expense = expense,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Author", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Profile(
            name = budgetRequest.requestingOfficer.fullName,
            background = budgetRequest.requestingOfficer.profileBackground,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Requesting Organization", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Profile(
            name = budgetRequest.requestingOrganization.name,
            background = budgetRequest.requestingOrganization.profileBackground,
        )
    }
}

@Preview
@Composable
fun BudgetRequestDetailsPreview() {
    BudgetRequestsTheme {
        BudgetRequestDetails(budgetRequest = DataSource.budgetRequests.first())
    }
}