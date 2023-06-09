package com.thebrownfoxx.budgetrequests.ui.screens.user

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
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material.icons.filled.Password
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
import com.thebrownfoxx.budgetrequests.ui.models.user.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.screens.home.budgetrequests.InfoChip
import com.thebrownfoxx.budgetrequests.ui.shared.IconTextSelection
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIcon
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIconSize
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun UserScreen(
    user: User,
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
                        modifier = Modifier.widthIn(max = 512.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            ProfileIcon(
                                text = user.fullName.first().toString(),
                                background = user.profileBackground,
                                size = ProfileIconSize.Large,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = user.fullName,
                                style = MaterialTheme.typography.displaySmall,
                            )
                            Text(text = "@${user.username}")
                            InfoChip(text = if (user is Admin) "Admin" else "Officer")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        IconTextSelection(
                            icon = Icons.Default.Badge,
                            text = "Change username",
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth()
                        )
                        IconTextSelection(
                            icon = Icons.Default.Password,
                            text = "Change password",
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth()
                        )
                        IconTextSelection(
                            icon = Icons.Default.Draw,
                            text = "Change signature",
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UserScreenPreview() {
    BudgetRequestsTheme {
        UserScreen(user = SampleDataSource.herbErt, onClose = {})
    }
}