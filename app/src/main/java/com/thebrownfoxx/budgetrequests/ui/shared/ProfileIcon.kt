package com.thebrownfoxx.budgetrequests.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme
import kotlin.random.Random
import kotlin.random.nextLong

@Composable
fun ProfileIcon(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = Color(Random.nextLong(0x64000000.toLong()..0x64FFFFFF)),
        modifier = modifier.size(24.dp).clip(CircleShape).background(Color(0xFF000000)),
    ) {
        Box(modifier = Modifier) {
            Text(
                text = text,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center),
                color = Color(0xFFFFFFFF),
            )
        }
    }
}

@Preview
@Composable
fun ProfileIconPreview() {
    BudgetRequestsTheme {
        ProfileIcon("A")
    }
}