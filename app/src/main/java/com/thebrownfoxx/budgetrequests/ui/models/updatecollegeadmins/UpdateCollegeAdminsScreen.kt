package com.thebrownfoxx.budgetrequests.ui.models.updatecollegeadmins

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
import com.thebrownfoxx.budgetrequests.data.datasource.EmptyDataSource
import com.thebrownfoxx.budgetrequests.ui.models.user.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.CollegeAdmins
import com.thebrownfoxx.budgetrequests.ui.screens.addorganization.UserPicker
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun UpdateCollegeAdminsScreen(
    onClose: () -> Unit,
) {
    val dataSource = EmptyDataSource

    val admins = dataSource.getAdmins()
    val collegeAdmins = dataSource.collegeAdmins

    var assistantDean by remember { mutableStateOf(collegeAdmins.assistantDean) }
    var dean by remember { mutableStateOf(collegeAdmins.dean) }
    var studentAffairsDirector by remember { mutableStateOf(collegeAdmins.studentAffairsDirector) }

    var assistantDeanError by remember { mutableStateOf<String?>(null) }
    var deanError by remember { mutableStateOf<String?>(null) }
    var studentAffairsDirectorError by remember { mutableStateOf<String?>(null) }

    var assistantDeanExpanded by remember { mutableStateOf(false) }
    var deanExpanded by remember { mutableStateOf(false) }
    var studentAffairsDirectorExpanded by remember { mutableStateOf(false) }

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
                            text = "Update College Admins",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    UserPicker(
                        label = "AssistantDean",
                        users = admins,
                        selectedUser = assistantDean,
                        onSelectedUserChange = { assistantDean = it as Admin },
                        expanded = assistantDeanExpanded,
                        onExpandedChange = { assistantDeanExpanded = it },
                        error = assistantDeanError,
                        onErrorReset = { assistantDeanError = null },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "Dean",
                        users = admins,
                        selectedUser = dean,
                        onSelectedUserChange = { dean = it as Admin },
                        expanded = deanExpanded,
                        onExpandedChange = { deanExpanded = it },
                        error = deanError,
                        onErrorReset = { deanError = null },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserPicker(
                        label = "StudentAffairsDirector",
                        users = admins,
                        selectedUser = studentAffairsDirector,
                        onSelectedUserChange = { studentAffairsDirector = it as Admin },
                        expanded = studentAffairsDirectorExpanded,
                        onExpandedChange = { studentAffairsDirectorExpanded = it },
                        error = studentAffairsDirectorError,
                        onErrorReset = { studentAffairsDirectorError = null },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            dataSource.updateCollegeAdmins(
                                CollegeAdmins(
                                    assistantDean = assistantDean,
                                    dean = dean,
                                    studentAffairsDirector = studentAffairsDirector,
                                )
                            )
                            onClose()
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
fun UpdateCollegeAdminsScreenPreview() {
    BudgetRequestsTheme {
        UpdateCollegeAdminsScreen(onClose = {})
    }
}