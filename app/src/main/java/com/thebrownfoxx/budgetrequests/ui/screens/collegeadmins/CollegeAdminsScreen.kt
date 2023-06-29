package com.thebrownfoxx.budgetrequests.ui.screens.collegeadmins

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.datasource.SampleDataSource
import com.thebrownfoxx.budgetrequests.ui.models.user.CollegeAdmins
import com.thebrownfoxx.budgetrequests.ui.shared.UserWithRole
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun CollegeAdminsScreen(
    collegeAdmins: CollegeAdmins,
    onNavigateToEditCollegeAdmins: () -> Unit,
    onClose: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(max = 512.dp)
            ) {
                Column(modifier = Modifier.padding(32.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onClose) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "College Admins",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    UserWithRole(user = collegeAdmins.assistantDean, role = "Assistant Dean")
                    Spacer(modifier = Modifier.height(8.dp))
                    UserWithRole(user = collegeAdmins.dean, role = "Dean")
                    Spacer(modifier = Modifier.height(8.dp))
                    UserWithRole(user = collegeAdmins.studentAffairsDirector, role = "Student Affairs Director")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onNavigateToEditCollegeAdmins,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Edit College Admins")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CollegeAdminsScreenPreview() {
    BudgetRequestsTheme {
        CollegeAdminsScreen(collegeAdmins = SampleDataSource.collegeAdmins, onNavigateToEditCollegeAdmins = {}, onClose = {})
    }
}