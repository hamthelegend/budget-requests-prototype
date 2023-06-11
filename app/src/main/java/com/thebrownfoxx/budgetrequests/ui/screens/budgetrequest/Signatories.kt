package com.thebrownfoxx.budgetrequests.ui.screens.budgetrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.DataSource
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.AdminSignatory
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.OfficerSignatory
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.Signatories
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.toSignatories
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIcon
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun Signatories(
    signatories: Signatories,
    currentUser: User,
    onSignatoriesChange: (Signatories) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "Signatories",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        signatories.toList().forEachIndexed { index, signatory ->
            if (index != 0) Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ProfileIcon(
                    text = signatory.user.firstName.first().toString(),
                    background = signatory.user.profileBackground,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = signatory.user.fullName,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Text(
                        text = signatory.role,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (/* TODO: implement this: signatory.hasReceivedRequest */ true) {
                    if (signatory.user == currentUser) {
                        IconButton(
                            onClick = {
                                val newSignatories = signatories.toList().toMutableList()
                                if (signatory is AdminSignatory) {
                                    newSignatories[index] = signatory.copy(hasSigned = false)
                                } else if (signatory is OfficerSignatory) {
                                    newSignatories[index] = signatory.copy(hasSigned = false)
                                }
                                onSignatoriesChange(newSignatories.toSignatories(signatories.adviser.user))
                            },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    if (signatory.hasSigned) Color.Transparent
                                    else MaterialTheme.colorScheme.primary
                                ),
                        ) {
                            Icon(
                                imageVector = Icons.Default.ThumbDown,
                                contentDescription = "",
                                tint = if (!signatory.hasSigned) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                            )
                        }
                        IconButton(
                            onClick = {
                                val newSignatories = signatories.toList().toMutableList()
                                if (signatory is AdminSignatory) {
                                    newSignatories[index] = signatory.copy(hasSigned = true)
                                } else if (signatory is OfficerSignatory) {
                                    newSignatories[index] = signatory.copy(hasSigned = true)
                                }
                                onSignatoriesChange(newSignatories.toSignatories(signatories.adviser.user))
                            },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    if (!signatory.hasSigned) Color.Transparent
                                    else MaterialTheme.colorScheme.primary
                                ),
                        ) {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = "",
                                tint = if (signatory.hasSigned) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    } else {
                        Text(text = if (signatory.hasSigned) "Signed" else "Not signed")
                        IconButton(
                            onClick = {},
                            enabled = false,
                        ) {
                            Icon(
                                imageVector = if (signatory.hasSigned) Icons.Default.ThumbUp else Icons.Default.ThumbDown,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignatoriesPreview() {
    var signatories by remember { mutableStateOf(DataSource.budgetRequests.first().signatories) }

    BudgetRequestsTheme {
        Signatories(
            signatories = signatories,
            onSignatoriesChange = { signatories = it },
            currentUser = DataSource.users.first(),
        )
    }
}