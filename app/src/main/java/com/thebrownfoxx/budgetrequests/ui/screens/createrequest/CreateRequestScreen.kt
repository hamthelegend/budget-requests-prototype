package com.thebrownfoxx.budgetrequests.ui.screens.createrequest

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.thebrownfoxx.budgetrequests.data.datasource.EmptyDataSource
import com.thebrownfoxx.budgetrequests.data.datasource.SampleDataSource
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.Expense
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.models.user.Officer
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CreateRequestScreen(
    loggedInOfficer: Officer,
    onAddBudgetRequest: (BudgetRequest) -> Unit,
    onClose: () -> Unit,
) {
    val dataSource = EmptyDataSource
    val organizations = remember { dataSource.getOrganizations(loggedInOfficer) }

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var organization by remember { mutableStateOf<Organization?>(null) }

    var purpose by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    var expenses by remember { mutableStateOf(listOf<Expense>()) }

    var titleError by remember { mutableStateOf<String?>(null) }
    var bodyError by remember { mutableStateOf<String?>(null) }
    var organizationError by remember { mutableStateOf<String?>(null) }
    var purposeError by remember { mutableStateOf<String?>(null) }
    var amountError by remember { mutableStateOf<String?>(null) }

    var organizationsExpanded by remember { mutableStateOf(false) }

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier.align(Alignment.Center)) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .widthIn(max = 512.dp)
                ) {
                    Row {
                        IconButton(onClick = onClose) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Create Budget Request",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            title = it
                            titleError = null
                        },
                        label = { Text(text = "Title") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = titleError != null,
                        supportingText = titleError?.let { titleError ->
                            { Text(text = titleError) }
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = body,
                        onValueChange = {
                            body = it
                            bodyError = null
                        },
                        label = { Text(text = "Body") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 200.dp),
                        isError = bodyError != null,
                        supportingText = bodyError?.let { bodyError ->
                            { Text(text = bodyError) }
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OrganizationPicker(
                        label = "Organization",
                        organizations = organizations,
                        selectedOrganization = organization,
                        onSelectedOrganizationChange = { organization = it },
                        expanded = organizationsExpanded,
                        onExpandedChange = { organizationsExpanded = it },
                        error = organizationError,
                        onErrorReset = { organizationError = null },
                    )
                    Column(modifier = Modifier.animateContentSize()) {
                        expenses.forEach { expense ->
                            Spacer(modifier = Modifier.height(8.dp))
                            ExpenseChip(
                                expense = expense,
                                onDelete = { expenses = expenses - expense },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        OutlinedTextField(
                            value = purpose,
                            onValueChange = {
                                purpose = it
                                purposeError = null
                            },
                            label = { Text(text = "Expense") },
                            modifier = Modifier.weight(2f),
                            singleLine = true,
                            isError = purposeError != null,
                            supportingText = purposeError?.let { purposeError ->
                                { Text(text = purposeError) }
                            },
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        OutlinedTextField(
                            value = amount,
                            onValueChange = {
                                amount = it
                                amountError = null
                            },
                            label = { Text(text = "Amount", maxLines = 1) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            isError = amountError != null,
                            supportingText = amountError?.let { amountError ->
                                { Text(text = amountError) }
                            },
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Surface(
                            onClick = {
                                var hasError = false

                                if (purpose == "") {
                                    hasError = true
                                    purposeError = "Purpose is required"
                                }

                                val amountDouble = try {
                                    amount.toDouble()
                                } catch (e: NumberFormatException) {
                                    null
                                }

                                if (amountDouble == null) {
                                    hasError = true
                                    amountError = "Amount needs to be a number"
                                }

                                if (!hasError) {
                                    expenses = expenses + Expense(purpose, amount.toDouble())
                                    purpose = ""
                                    amount = ""
                                }
                            },
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            var hasError = false

                            if (title == "") {
                                hasError = true
                                titleError = "Title required"
                            }

                            if (body == "") {
                                hasError = true
                                titleError = "Body required"
                            }

                            if (organization == null) {
                                hasError = true
                                titleError = "Organization required"
                            }
                            
                            if (!hasError) {
                                val budgetRequest = BudgetRequest(
                                    title = title,
                                    body = body,
                                    expenses = expenses,
                                    requestingOrganization = organization!!,
                                    requestingOfficer = loggedInOfficer,
                                    signatories = dataSource.getDefaultSignatories(organization!!)
                                )
                                onAddBudgetRequest(budgetRequest)
                                onClose()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Submit Request")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateRequestPreview() {
    BudgetRequestsTheme {
        CreateRequestScreen(
            loggedInOfficer = SampleDataSource.jerIcho,
            onClose = {},
            onAddBudgetRequest = {},
        )
    }
}