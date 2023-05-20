package com.thebrownfoxx.budgetrequests.ui.screens.home.budgetrequests

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.BudgetRequest
import com.thebrownfoxx.budgetrequests.data.sampleBudgetRequests
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun BudgetRequests(
    budgetRequests: List<BudgetRequest>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    LazyColumn(modifier = modifier.padding(horizontal = paddingValues.calculateStartPadding(LayoutDirection.Rtl))) {
        itemsIndexed(budgetRequests) { index, budgetRequest ->
            Spacer(modifier = Modifier.height(if (index == 0) paddingValues.calculateTopPadding() else 16.dp))
            BudgetRequestCard(budgetRequest = budgetRequest, onClick = onClick)
            if (index == budgetRequests.lastIndex) Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
        }
    }
}

@Preview
@Composable
fun BudgetRequestsPreview() {
    BudgetRequestsTheme {
        BudgetRequests(budgetRequests = sampleBudgetRequests, onClick = {})
    }
}