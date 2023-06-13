package com.thebrownfoxx.budgetrequests.ui.screens.budgetrequest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.dataSource
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.Signatories
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.signatory.toSignatories
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.shared.ExpandableButton
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
        signatories.toMap().toList().forEachIndexed { index, (position, signatory) ->
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
                        text = signatories.getSignatoryPosition(signatory).text,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (/* TODO: implement this: signatory.hasReceivedRequest */ true) {
                    if (signatory.user == currentUser) {
                        ExpandableButton(
                            icon = Icons.Default.ThumbDown,
                            text = "Unsign",
                            active = !signatory.hasSigned,
                            onClick = {
                                val newSignatories = signatories.toMap().toMutableMap()
                                newSignatories[position] = signatory.unsigned()
                                onSignatoriesChange(newSignatories.toSignatories())
                            },
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ExpandableButton(
                            icon = Icons.Default.ThumbUp,
                            text = "Sign",
                            active = signatory.hasSigned,
                            onClick = {
                                val newSignatories = signatories.toMap().toMutableMap()
                                newSignatories[position] = signatory.signed()
                                onSignatoriesChange(newSignatories.toSignatories())
                            },
                        )
                    } else {
                        ExpandableButton(
                            icon = if (signatory.hasSigned) Icons.Default.ThumbUp else Icons.Default.ThumbDown,
                            text = if (signatory.hasSigned) "Signed" else "Not signed",
                            active = signatory.hasSigned,
                            onClick = {},
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignatoriesPreview() {
    var signatories by remember { mutableStateOf(dataSource.budgetRequests.first().signatories) }

    BudgetRequestsTheme {
        Signatories(
            signatories = signatories,
            onSignatoriesChange = { signatories = it },
            currentUser = dataSource.users.first(),
        )
    }
}