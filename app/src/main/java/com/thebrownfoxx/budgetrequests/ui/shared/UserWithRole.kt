package com.thebrownfoxx.budgetrequests.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.ui.models.user.User

@Composable
fun UserWithRole(
    user: User?,
    role: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProfileIcon(
            text = user?.firstName?.first()?.toString() ?: "?",
            background = user?.profileBackground ?: Color(0x64FF0000),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = role,
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = user?.fullName ?: "Unassigned",
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}