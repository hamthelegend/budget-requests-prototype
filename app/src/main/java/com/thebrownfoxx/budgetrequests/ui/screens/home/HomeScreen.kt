package com.thebrownfoxx.budgetrequests.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.sampleBudgetRequests
import com.thebrownfoxx.budgetrequests.ui.screens.home.budgetrequests.BudgetRequests
import com.thebrownfoxx.budgetrequests.ui.screens.home.createbutton.PrimaryButton
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme
import com.thebrownfoxx.budgetrequests.ui.screens.home.search.SearchBar
import com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar.SideBar
import com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar.SideBarButton

@Composable
fun HomeScreen(
    onCreateRequest: () -> Unit,
    onLogout: () -> Unit,
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .width(384.dp)
                    .padding(top = 32.dp, bottom = 32.dp, start = 0.dp, end = 0.dp),
            ) {
                PrimaryButton(
                    icon = Icons.Default.Create,
                    text = "Create Request",
                    onClick = onCreateRequest,
                    modifier = Modifier.padding(start = 16.dp).fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                SideBar(paddingValues = PaddingValues(start = 32.dp), modifier = Modifier.weight(1f))
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
                SearchBar(modifier = Modifier
                    .padding(top = 32.dp, start = 32.dp, end = 32.dp, bottom = 16.dp)
                    .fillMaxWidth())
                BudgetRequests(
                    budgetRequests = sampleBudgetRequests,
                    paddingValues = PaddingValues(start = 32.dp, end = 32.dp, bottom = 32.dp),
                )
            }
        }
    }
}

@Preview(widthDp = 1920, heightDp = 1080)
@Composable
fun HomeScreenPreview() {
    BudgetRequestsTheme {
        HomeScreen(onLogout = {}, onCreateRequest = {})
    }
}