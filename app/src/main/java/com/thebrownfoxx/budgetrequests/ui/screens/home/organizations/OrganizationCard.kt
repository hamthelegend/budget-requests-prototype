package com.thebrownfoxx.budgetrequests.ui.screens.home.organizations

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIcon
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIconSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationCard(
    organization: Organization,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(32.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileIcon(
                text = organization.name.first().toString(),
                background = organization.profileBackground,
                size = ProfileIconSize.Medium,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = organization.name,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}