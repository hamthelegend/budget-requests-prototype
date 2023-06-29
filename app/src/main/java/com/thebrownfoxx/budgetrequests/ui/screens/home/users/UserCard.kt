package com.thebrownfoxx.budgetrequests.ui.screens.home.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.datasource.SampleDataSource
import com.thebrownfoxx.budgetrequests.ui.models.user.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIcon
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIconSize
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    user: User,
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
                text = user.fullName.first().toString(),
                background = user.profileBackground,
                size = ProfileIconSize.Medium,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = user.fullName,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = if (user is Admin) "Admin" else "Officer",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Preview
@Composable
fun UserCardPreview() {
    BudgetRequestsTheme {
        UserCard(user = SampleDataSource.blyThe, onClick = {}, modifier = Modifier.fillMaxWidth())
    }
}