package com.thebrownfoxx.budgetrequests.ui.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@Composable
fun ExpandableButton(
    icon: ImageVector,
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
        Icon(
            imageVector = icon,
            contentDescription = text,
        )
        AnimatedVisibility(visible = !active) {
            Row {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = text, maxLines = 1)
            }
        }
    }
}

@Preview
@Composable
fun ExpandableButtonPreview() {
    var active by remember { mutableStateOf(false) }

    BudgetRequestsTheme {
        ExpandableButton(
            icon = Icons.Default.ThumbUp,
            text = "Like",
            active = active,
            onClick = { active = !active }
        )
    }
}