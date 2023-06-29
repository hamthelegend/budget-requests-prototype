package com.thebrownfoxx.budgetrequests.ui.screens.createrequest

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.shared.Profile
import com.thebrownfoxx.budgetrequests.ui.shared.ProfileIcon

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun OrganizationPicker(
    label: String,
    organizations: List<Organization>,
    selectedOrganization: Organization?,
    onSelectedOrganizationChange: (Organization) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    error: String?,
    onErrorReset: () -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange(it) },
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOrganization?.name ?: "",
            onValueChange = {},
            leadingIcon = selectedOrganization?.let { selectedOrganization ->
                {
                    ProfileIcon(
                        text = selectedOrganization.name.first().toString(),
                        background = selectedOrganization.profileBackground,
                    )
                }
            },
            label = { Text(text = label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            isError = error != null,
            supportingText = error?.let { error ->
                { Text(text = error) }
            },
        )
        Spacer(modifier = Modifier.height(4.dp))
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
        ) {
            organizations.forEach { organization ->
                DropdownMenuItem(
                    text = {
                        Profile(
                            name = organization.name,
                            background = organization.profileBackground,
                        )
                    },
                    onClick = {
                        onSelectedOrganizationChange(organization)
                        onExpandedChange(false)
                        onErrorReset()
                    }
                )
            }
        }
    }
}