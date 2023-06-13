package com.thebrownfoxx.budgetrequests.ui.screens.home.users

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.SampleDataSource
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.shared.IconText
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun UsersPage(
    users: List<User>,
    onUserClick: (User) -> Unit,
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
        itemsIndexed(users) { index, user ->
            Spacer(modifier = Modifier.height(if (index == 0) paddingValues.calculateTopPadding() else 16.dp))
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
        )
    }
}