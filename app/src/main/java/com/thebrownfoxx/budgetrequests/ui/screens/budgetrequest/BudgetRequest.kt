package com.thebrownfoxx.budgetrequests.ui.screens.budgetrequest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.dataSource
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun BudgetRequestScreen(
    budgetRequest: BudgetRequest,
    onBudgetRequestChange: (BudgetRequest) -> Unit,
    onClose: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier
                .align(Alignment.Center)
                .widthIn(max = 1024.dp)) {
                Column(modifier = Modifier.padding(32.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onClose) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = budgetRequest.title,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        BudgetRequestDetails(
                            budgetRequest = budgetRequest,
                            modifier = Modifier
                                .weight(1f),
                        )
                        Spacer(modifier = Modifier.width(32.dp))
                        Signatories(
                            signatories = budgetRequest.signatories,
                            currentUser = dataSource.users.first(),
                            onSignatoriesChange = { signatories ->
                                val newBudgetRequest = budgetRequest.copy(signatories = signatories)
                                onBudgetRequestChange(newBudgetRequest)
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Edit Request")
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 1920)
@Composable
fun BudgetRequestPreview() {
    var budgetRequest by remember { mutableStateOf(dataSource.budgetRequests.first()) }

    BudgetRequestsTheme {
        BudgetRequestScreen(
            budgetRequest = budgetRequest,
            onBudgetRequestChange = { budgetRequest = it },
            onClose = {},
        )
    }
}