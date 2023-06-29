package com.thebrownfoxx.budgetrequests.ui.screens.addorganization

import androidx.compose.animation.animateContentSize
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
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.models.organization.OrganizationOfficers
import com.thebrownfoxx.budgetrequests.ui.models.user.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.Officer
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrganizationScreen(
    onAddOrganization: (Organization) -> Unit,
    onClose: () -> Unit,
) {
    val dataSource = EmptyDataSource

    val admins = remember { dataSource.getAdmins() }
    val officers = remember { dataSource.getOfficers() }

    var name by remember { mutableStateOf("") }
    var adviser by remember { mutableStateOf<Admin?>(null) }
    var president by remember { mutableStateOf<Officer?>(null) }
    var vicePresident by remember { mutableStateOf<Officer?>(null) }
    var secretary by remember { mutableStateOf<Officer?>(null) }
    var treasurer by remember { mutableStateOf<Officer?>(null) }
    var auditor by remember { mutableStateOf<Officer?>(null) }
    var publicRelationsOfficer by remember { mutableStateOf<Officer?>(null) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var adviserError by remember { mutableStateOf<String?>(null) }
    var presidentError by remember { mutableStateOf<String?>(null) }
    var vicePresidentError by remember { mutableStateOf<String?>(null) }
    var secretaryError by remember { mutableStateOf<String?>(null) }
    var treasurerError by remember { mutableStateOf<String?>(null) }
    var auditorError by remember { mutableStateOf<String?>(null) }
    var publicRelationsOfficerError by remember { mutableStateOf<String?>(null) }

    var adviserExpanded by remember { mutableStateOf(false) }
    var presidentExpanded by remember { mutableStateOf(false) }
    var vicePresidentExpanded by remember { mutableStateOf(false) }
    var secretaryExpanded by remember { mutableStateOf(false) }
    var treasurerExpanded by remember { mutableStateOf(false) }
    var auditorExpanded by remember { mutableStateOf(false) }
    var publicRelationsOfficerExpanded by remember { mutableStateOf(false) }

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier.align(Alignment.Center)) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .widthIn(max = 384.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onClose) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Add Organization",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            nameError = null
                        },
                        label = { Text(text = "Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        singleLine = true,
                        isError = nameError != null,
                        supportingText = nameError?.let { firstNameError ->
                            { Text(text = firstNameError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "Adviser",
                        users = admins,
                        selectedUser = adviser,
                        onSelectedUserChange = { adviser = it as Admin },
                        expanded = adviserExpanded,
                        onExpandedChange = { adviserExpanded = it },
                        error = adviserError,
                        onErrorReset = { adviserError = null },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "President",
                        users = officers,
                        selectedUser = president,
                        onSelectedUserChange = { president = it as Officer },
                        expanded = presidentExpanded,
                        onExpandedChange = { presidentExpanded = it },
                        error = presidentError,
                        onErrorReset = { presidentError = null },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "Vice President",
                        users = officers,
                        selectedUser = vicePresident,
                        onSelectedUserChange = { vicePresident = it as Officer },
                        expanded = vicePresidentExpanded,
                        onExpandedChange = { vicePresidentExpanded = it },
                        error = vicePresidentError,
                        onErrorReset = { vicePresidentError = null },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "Secretary",
                        users = officers,
                        selectedUser = secretary,
                        onSelectedUserChange = { secretary = it as Officer },
                        expanded = secretaryExpanded,
                        onExpandedChange = { secretaryExpanded = it },
                        error = secretaryError,
                        onErrorReset = { secretaryError = null },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "Treasurer",
                        users = officers,
                        selectedUser = treasurer,
                        onSelectedUserChange = { treasurer = it as Officer },
                        expanded = treasurerExpanded,
                        onExpandedChange = { treasurerExpanded = it },
                        error = treasurerError,
                        onErrorReset = { treasurerError = null },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "Auditor",
                        users = officers,
                        selectedUser = auditor,
                        onSelectedUserChange = { auditor = it as Officer },
                        expanded = auditorExpanded,
                        onExpandedChange = { auditorExpanded = it },
                        error = auditorError,
                        onErrorReset = { auditorError = null },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "P.R.O.",
                        users = officers,
                        selectedUser = publicRelationsOfficer,
                        onSelectedUserChange = { publicRelationsOfficer = it as Officer },
                        expanded = publicRelationsOfficerExpanded,
                        onExpandedChange = { publicRelationsOfficerExpanded = it },
                        error = publicRelationsOfficerError,
                        onErrorReset = { publicRelationsOfficerError = null },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            var hasError = false

                            if (name == "") {
                                hasError = true
                                nameError = "First name required"
                            }

                            if (adviser == null) {
                                hasError = true
                                adviserError = "Adviser is required"
                            }

                            if (president == null) {
                                hasError = true
                                presidentError = "President is required"
                            }

                            if (vicePresident == null) {
                                hasError = true
                                vicePresidentError = "Vice President is required"
                            }

                            if (secretary == null) {
                                hasError = true
                                secretaryError = "Secretary is required"
                            }

                            if (treasurer == null) {
                                hasError = true
                                treasurerError = "Treasurer is required"
                            }

                            if (auditor == null) {
                                hasError = true
                                auditorError = "Auditor is required"
                            }

                            if (publicRelationsOfficer == null) {
                                hasError = true
                                publicRelationsOfficerError = "P.R.O. is required"
                            }

                            if (!hasError) {
                                val organization = Organization(
                                    name = name,
                                    adviser = adviser!!,
                                    officers = OrganizationOfficers(
                                        president = president!!,
                                        vicePresident = vicePresident!!,
                                        secretary = secretary!!,
                                        treasurer = treasurer!!,
                                        auditor = auditor!!,
                                        publicRelationsOfficer = publicRelationsOfficer!!,
                                    )
                                )
                                onAddOrganization(organization)
                                onClose()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Login")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddOrganizationScreenPreview() {
    BudgetRequestsTheme {
        AddOrganizationScreen(onAddOrganization = {}, onClose = {})
    }
}