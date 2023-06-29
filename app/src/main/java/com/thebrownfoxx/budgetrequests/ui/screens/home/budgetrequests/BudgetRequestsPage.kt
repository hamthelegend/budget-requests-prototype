package com.thebrownfoxx.budgetrequests.ui.screens.home.budgetrequests

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.datasource.SampleDataSource
import com.thebrownfoxx.budgetrequests.ui.models.budgetrequest.BudgetRequest
import com.thebrownfoxx.budgetrequests.ui.shared.IconText
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun BudgetRequestsPage(
    budgetRequests: List<BudgetRequest>,
    onBudgetRequestClick: (BudgetRequest) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    if (budgetRequests.isEmpty()) {
        IconText(
            icon = Icons.Default.Inbox,
            text = "You have no budget requests",
            modifier = Modifier.fillMaxSize(),
        )
    }
    LazyColumn(modifier = modifier.padding(horizontal = paddingValues.calculateStartPadding(LayoutDirection.Rtl))) {
        itemsIndexed(budgetRequests) { index, budgetRequest ->
            Spacer(modifier = Modifier.height(if (index == 0) paddingValues.calculateTopPadding() else 16.dp))
            BudgetRequestCard(budgetRequest = budgetRequest, onClick = { onBudgetRequestClick(budgetRequest) })
            if (index == budgetRequests.lastIndex) Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
        }
    }
}

@Preview
@Composable
fun BudgetRequestsPagePreview() {
    BudgetRequestsTheme {
        BudgetRequestsPage(budgetRequests = SampleDataSource.budgetRequests, onBudgetRequestClick = {})
    }
}