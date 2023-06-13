package com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.RequestPage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.thebrownfoxx.budgetrequests.ui.screens.home.HomePage
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

val sideBarOptions = listOf(
    SideBarOption(
        icon = Icons.Default.RequestPage,
        text = "Budget Requests",
        homePage = HomePage.BudgetRequests,
        forAdmins = true,
        forOfficers = true,
    ),
    SideBarOption(
        icon = Icons.Default.Notifications,
        text = "Notifications",
        homePage = HomePage.Notifications,
        forAdmins = true,
        forOfficers = true,
    ),
    SideBarOption(
        icon = Icons.Default.People,
        text = "Users",
        homePage = HomePage.Users,
        forAdmins = true,
        forOfficers = false,
    ),
    SideBarOption(
        icon = Icons.Default.Groups,
        text = "Organizations",
        homePage = HomePage.Organizations,
        forAdmins = true,
        forOfficers = true,
    ),
)

data class SideBarOption(
    val icon: ImageVector,
    val text: String,
    val homePage: HomePage,
    val forAdmins: Boolean,
    val forOfficers: Boolean,
)

@Composable
fun SideBar(
    isAdmin: Boolean,
    activeOption: SideBarOption,
    onOptionClick: (SideBarOption) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    Column(modifier = modifier.fillMaxSize()) {
        sideBarOptions.forEach { sideBarOption ->
            if (isAdmin && sideBarOption.forAdmins || !isAdmin && sideBarOption.forOfficers) {
                SideBarButton(
                    icon = sideBarOption.icon,
                    text = sideBarOption.text,
                    onClick = {
                        onOptionClick(sideBarOption)
                    },
                    active = sideBarOption == activeOption,
                    modifier = Modifier.fillMaxWidth(),
                    paddingValues = paddingValues,
                )
            }
        }
    }
}

@Preview
@Composable
fun SideBarPreview() {
    var option by remember { mutableStateOf(sideBarOptions.first()) }

    BudgetRequestsTheme {
        SideBar(
            isAdmin = false,
            onOptionClick = { option = it },
            activeOption = option,
        )
    }
}