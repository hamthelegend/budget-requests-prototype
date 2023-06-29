package com.thebrownfoxx.budgetrequests.ui.screens.home.organizations

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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.ui.models.organization.Organization
import com.thebrownfoxx.budgetrequests.ui.shared.IconText

@Composable
fun OrganizationsPage(
    organizations: List<Organization>,
    onOrganizationClick: (Organization) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    if (organizations.isEmpty()) {
        IconText(
            icon = Icons.Default.Inbox,
            text = "There are no organizations",
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
        itemsIndexed(organizations) { index, organization ->
            Spacer(modifier = Modifier.height(if (index == 0) paddingValues.calculateTopPadding() else 16.dp))
            OrganizationCard(organization = organization, onClick = { onOrganizationClick(organization) }, modifier = Modifier.fillMaxWidth())
            if (index == organizations.lastIndex) Spacer(modifier = androidx.compose.ui.Modifier.height(paddingValues.calculateBottomPadding()))
        }
    }
}