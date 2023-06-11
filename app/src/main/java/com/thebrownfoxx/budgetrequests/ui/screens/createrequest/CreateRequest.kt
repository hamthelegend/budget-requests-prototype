package com.thebrownfoxx.budgetrequests.ui.screens.createrequest

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.DataSource
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.Expense
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CreateRequestScreen(
    onClose: () -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var organizationsDropdownExpanded by remember { mutableStateOf(false) }
    var selectedOrganization by remember { mutableStateOf<Organization?>(null) }
    var expenses by remember { mutableStateOf(listOf<Expense>()) }

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
                        onValueChange = { title = it },
                        label = { Text(text = "Title") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = body,
                        onValueChange = { body = it },
                        label = { Text(text = "Body") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 200.dp),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                        OutlinedTextField(
                            value = selectedOrganization?.name ?: "",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(text = "Organization") },
                            modifier = Modifier.fillMaxWidth(),
                            interactionSource = remember { MutableInteractionSource() }
                                .also { interactionSource ->
                                    LaunchedEffect(interactionSource) {
                                        interactionSource.interactions.collect {
                                            if (it is PressInteraction.Release) {
                                                organizationsDropdownExpanded = !organizationsDropdownExpanded
                                            }
                                        }
                                    }
                                },
                            trailingIcon = {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Localized description"
                                )
                            }
                        )
                        DropdownMenu(
                            expanded = organizationsDropdownExpanded,
                            onDismissRequest = { organizationsDropdownExpanded = false },
                        ) {
                            DataSource.organizations.forEach { organization ->
                                DropdownMenuItem(
                                    text = { Text(text = organization.name) },
                                    onClick = {
                                        selectedOrganization = organization
                                        organizationsDropdownExpanded = false
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                        }
                    }
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
                        var purpose by remember { mutableStateOf("") }
                        var amount by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = purpose,
                            onValueChange = { purpose = it },
                            label = { Text(text = "Expense") },
                            modifier = Modifier.weight(2f),
                            singleLine = true,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        OutlinedTextField(
                            value = amount,
                            onValueChange = { amount = it },
                            label = { Text(text = "Amount", maxLines = 1) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Surface(
                            onClick = { expenses = expenses + Expense(purpose, amount.toDouble()) },
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
                        onClick = onClose,
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
        CreateRequestScreen(onClose = {})
    }
}