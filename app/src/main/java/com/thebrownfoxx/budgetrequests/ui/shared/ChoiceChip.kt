package com.thebrownfoxx.budgetrequests.ui.shared

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun ChoiceChip(
    text: String,
    active: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        colors = when (active) {
            true -> ButtonDefaults.buttonColors()
            false -> ButtonDefaults.outlinedButtonColors()
        },
        modifier = modifier,
    ) {
        Text(text = text, maxLines = 1)
    }
}

@Preview
@Composable
fun ChoiceChipPreview() {
    var active by remember { mutableStateOf(false) }

    BudgetRequestsTheme {
        ChoiceChip(
            text = "Like",
            active = active,
            onClick = { active = !active }
        )
    }
}