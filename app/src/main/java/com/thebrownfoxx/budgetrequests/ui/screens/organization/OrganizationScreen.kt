package com.thebrownfoxx.budgetrequests.ui.screens.organization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.models.organization.OrganizationPosition
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIcon
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIconSize
import com.thebrownfoxx.budgetrequests.ui.shared.UserWithRole
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun OrganizationScreen(
    organization: Organization,
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
                    IconButton(onClick = onClose) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .widthIn(max = 512.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            ProfileIcon(
                                text = organization.name.first().toString(),
                                background = organization.profileBackground,
                                size = ProfileIconSize.Large,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = organization.name,
                                style = MaterialTheme.typography.displaySmall,
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        UserWithRole(user = organization.adviser, role = "Adviser")
                        for ((position, officer) in organization.officers.toMap()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            UserWithRole(
                                user = officer,
                                role = when (position) {
                                    OrganizationPosition.President -> "President"
                                    OrganizationPosition.VicePresident -> "Vice President"
                                    OrganizationPosition.Secretary -> "Secretary"
                                    OrganizationPosition.Treasurer -> "Treasurer"
                                    OrganizationPosition.Auditor -> "Auditor"
                                    OrganizationPosition.PublicRelationsOfficer -> "P.R.O."
                                },
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Edit Organization")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OrganizationScreenPreview() {
    BudgetRequestsTheme {
        OrganizationScreen(organization = SampleDataSource.honorsSociety, onClose = {})
    }
}