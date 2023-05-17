package com.thebrownfoxx.budgetrequests.ui.screens.home.budgetrequests

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoChip(text: String, modifier: Modifier = Modifier) {
    Chip(
        onClick = {},
        shape = CircleShape,
        modifier = modifier,
        colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview
@Composable
fun InfoChipPreview() {
    InfoChip(text = "₱ 1,000.00")
}