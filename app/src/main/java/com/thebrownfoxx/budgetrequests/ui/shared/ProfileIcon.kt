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

enum class ProfileIconSize {
    Small,
    Medium,
    Large,
}

@Composable
fun ProfileIcon(
    text: String,
    background: Color,
    modifier: Modifier = Modifier,
    size: ProfileIconSize = ProfileIconSize.Small,
) {
    Surface(
        color = background,
        modifier = modifier
            .size(
                when (size) {
                    ProfileIconSize.Small -> 24.dp
                    ProfileIconSize.Medium -> 48.dp
                    ProfileIconSize.Large -> 64.dp
                }
            )
            .clip(CircleShape)
            .background(Color(0xFF000000)),
    ) {
        Box(modifier = Modifier) {
            Text(
                text = text,
                fontSize = when (size) {
                    ProfileIconSize.Small -> 16.sp
                    ProfileIconSize.Medium -> 24.sp
                    ProfileIconSize.Large -> 32.sp
                },
                modifier = Modifier.align(Alignment.Center),
                color = Color(0xFFFFFFFF),
            )
        }
    }
}

@Preview
@Composable
fun SmallProfileIconPreview() {
    BudgetRequestsTheme {
        ProfileIcon(
            text = "A",
            background = Color(Random.nextLong(0x64000000.toLong()..0x64FFFFFF)),
            size = ProfileIconSize.Small,
        )
    }
}

@Preview
@Composable
fun MediumProfileIconPreview() {
    BudgetRequestsTheme {
        ProfileIcon(
            text = "A",
            background = Color(Random.nextLong(0x64000000.toLong()..0x64FFFFFF)),
            size = ProfileIconSize.Medium,
        )
    }
}

@Preview
@Composable
fun LargeProfileIconPreview() {
    BudgetRequestsTheme {
        ProfileIcon(
            text = "A",
            background = Color(Random.nextLong(0x64000000.toLong()..0x64FFFFFF)),
            size = ProfileIconSize.Large,
        )
    }
}