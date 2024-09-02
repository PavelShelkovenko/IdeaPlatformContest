package com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables

import androidx.compose.foundation.layout.height
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.theme.IdeaPlatformContestTheme


@Composable
fun TagChip(
    title: String,
    modifier: Modifier = Modifier
) {
    AssistChip(
        onClick = { },
        modifier = modifier.height(32.dp),
        label = {
            Text(text = title)
        }
    )

}

@Preview(showBackground = true)
@Composable
fun TagChipPreview() {
    IdeaPlatformContestTheme {
        TagChip("Распродажа")
    }
}