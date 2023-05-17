package com.thebrownfoxx.budgetrequests.ui.screens.home.sidebar

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

val sideBarOptions = listOf(
    SideBarOption(icon = Icons.Default.RequestPage, "My Requests"),
    SideBarOption(icon = Icons.Default.Checklist, "To Approve"),
    SideBarOption(icon = Icons.Default.Notifications, "Notifications"),
    SideBarOption(icon = Icons.Default.People, "Users"),
    SideBarOption(icon = Icons.Default.Groups, "Organizations"),
)

data class SideBarOption(
    val icon: ImageVector,
    val text: String,
)

@Composable
fun SideBar(modifier: Modifier = Modifier, paddingValues: PaddingValues = PaddingValues()) {
    Column(modifier = modifier.fillMaxSize()) {
        sideBarOptions.forEachIndexed { index, sideBarOption ->
            SideBarButton(
                icon = sideBarOption.icon,
                text = sideBarOption.text,
                onClick = {},
                active = index == 0,
                modifier = Modifier.fillMaxWidth(),
                paddingValues = paddingValues,
            )
        }
    }
}

@Preview
@Composable
fun SideBarPreview() {
    BudgetRequestsTheme {
        SideBar()
    }
}