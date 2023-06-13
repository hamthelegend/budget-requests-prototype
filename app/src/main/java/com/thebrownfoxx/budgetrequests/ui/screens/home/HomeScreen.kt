package com.thebrownfoxx.budgetrequests.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.EmptyDataSource
import com.thebrownfoxx.budgetrequests.data.SampleDataSource
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin
import com.thebrownfoxx.budgetrequests.ui.screens.home.budgetrequests.BudgetRequests
import com.thebrownfoxx.budgetrequests.ui.screens.home.createbutton.PrimaryButton
import com.thebrownfoxx.budgetrequests.ui.screens.home.search.SearchBar
import com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar.SideBar
import com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar.SideBarButton
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIcon
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    loggedInUser: User,
    onCreateRequest: () -> Unit,
    onLogout: () -> Unit,
    onBudgetRequestClick: (BudgetRequest) -> Unit,
) {
    val dataSource = EmptyDataSource

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .width(384.dp)
                    .padding(top = 32.dp, bottom = 32.dp, start = 0.dp, end = 0.dp),
            ) {
                if (loggedInUser !is Admin) {
                    PrimaryButton(
                        icon = Icons.Default.Create,
                        text = "Create Request",
                        onClick = onCreateRequest,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                SideBar(
                    paddingValues = PaddingValues(start = 32.dp),
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    shape = RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = 50,
                        bottomEndPercent = 50,
                        bottomStartPercent = 0
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onLogout,
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .padding(paddingValues = PaddingValues(start = 32.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ProfileIcon(
                            text = loggedInUser.fullName.first().toString(),
                            background = loggedInUser.profileBackground,
                            modifier = Modifier.size(24.dp),
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = loggedInUser.fullName,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
                SideBarButton(
                    paddingValues = PaddingValues(start = 32.dp),
                    icon = Icons.Default.Logout,
                    text = "Logout",
                    active = false,
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                SearchBar(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp, end = 32.dp, bottom = 16.dp)
                        .fillMaxWidth()
                )
                BudgetRequests(
                    budgetRequests = dataSource.budgetRequests,
                    paddingValues = PaddingValues(start = 32.dp, end = 32.dp, bottom = 32.dp),
                    onBudgetRequestClick = onBudgetRequestClick,
                )
            }
        }
    }
}

@Preview(widthDp = 1920, heightDp = 1080)
@Composable
fun HomeScreenPreview() {
    BudgetRequestsTheme {
        HomeScreen(
            loggedInUser = SampleDataSource.herbErt,
            onLogout = {},
            onCreateRequest = {},
            onBudgetRequestClick = {})
    }
}