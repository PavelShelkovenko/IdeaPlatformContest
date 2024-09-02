package com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.theme.IdeaPlatformContestTheme

@Composable
fun AmountInfo(
    amount: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "На складе",
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = amount
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AmountInfoPreview() {
    IdeaPlatformContestTheme {
        AmountInfo("30")
    }
}