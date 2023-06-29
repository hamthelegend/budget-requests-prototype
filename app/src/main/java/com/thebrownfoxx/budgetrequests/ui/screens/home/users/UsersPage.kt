package com.thebrownfoxx.budgetrequests.ui.screens.home.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.datasource.SampleDataSource
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.shared.IconText
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersPage(
    users: List<User>,
    onUserClick: (User) -> Unit,
    onNavigateToCollegeAdmins: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    if (users.isEmpty()) {
        IconText(
            icon = Icons.Default.Inbox,
            text = "There are no users",
            modifier = Modifier.fillMaxSize(),
        )
    }
    LazyColumn(
        modifier = modifier.padding(
            horizontal = paddingValues.calculateStartPadding(
                LayoutDirection.Rtl
            )
        ),
    ) {
        item {
            Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))
            Card(
                onClick = onNavigateToCollegeAdmins,
                modifier = modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.padding(32.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(imageVector = Icons.Default.ManageAccounts, contentDescription = "College admins")
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Manage admins",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
            }
        }
        itemsIndexed(users) { index, user ->
            Spacer(modifier = Modifier.height(16.dp))
            UserCard(user = user, onClick = { onUserClick(user) }, modifier = Modifier.fillMaxWidth())
            if (index == users.lastIndex) Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
        }
    }
}

@Preview
@Composable
fun UsersPagePreview() {
    BudgetRequestsTheme {
        UsersPage(
            users = SampleDataSource.users,
            onUserClick = {},
            onNavigateToCollegeAdmins = {},
        )
    }
}