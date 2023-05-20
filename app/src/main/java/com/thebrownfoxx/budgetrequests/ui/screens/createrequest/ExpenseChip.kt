package com.thebrownfoxx.budgetrequests.ui.screens.createrequest

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.Expense
import com.thebrownfoxx.budgetrequests.data.formattedMonetaryAmount
import com.thebrownfoxx.budgetrequests.data.sampleExpense
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpenseChip(
    expense: Expense,
    modifier: Modifier = Modifier,
    onDelete: (() -> Unit)? = null,
) {
    OutlinedButton (
        onClick = onDelete ?: {},
        shape = CircleShape,
        modifier = modifier,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = expense.purpose)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = expense.amount.formattedMonetaryAmount)
            if (onDelete != null) {
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove icon",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ExpenseChipPreview() {
    BudgetRequestsTheme {
        ExpenseChip(
            expense = sampleExpense,
            modifier = Modifier.fillMaxWidth(),
            onDelete = {},
        )
    }
}