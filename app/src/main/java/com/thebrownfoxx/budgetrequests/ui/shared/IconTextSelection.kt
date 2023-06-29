package com.thebrownfoxx.budgetrequests.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IconTextSelection(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = Color.Transparent,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
    ) {
       Row(
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.padding(16.dp),
       ) {
           Icon(imageVector = icon, contentDescription = text)
           Spacer(modifier = Modifier.width(16.dp))
           Column {
               Text(
                   text = text,
                   style = MaterialTheme.typography.titleLarge,
               )
           }
       }
    }
}

@Preview
@Composable
fun IconTextSelectionPreview() {
    BudgetRequestsTheme {
        IconTextSelection(icon = Icons.Default.PlayArrow, text = "Action", onClick = {}, modifier = Modifier.fillMaxWidth())
    }
}