package com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pavelshelkovenko.ideaplatformcontest.R
import com.pavelshelkovenko.ideaplatformcontest.presentation.models.ProductUi
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.theme.IdeaPlatformContestTheme
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.theme.LightRed
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.theme.Purple

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCard(
    product: ProductUi,
    onOpenChangingAmountDialog: (Int, Int) -> Unit,
    onOpenDeleteProductDialog: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                ambientColor = Color.DarkGray,
                spotColor = Color.DarkGray,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = product.name,
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onOpenChangingAmountDialog(product.id, product.amount.toInt())
                        },
                    tint = Purple,
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_button_content_description)
                )
                Spacer(Modifier.width(12.dp))
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onOpenDeleteProductDialog(product.id)
                        },
                    imageVector = Icons.Default.Delete,
                    tint = LightRed,
                    contentDescription = stringResource(R.string.delete_button_content_description),
                )
            }
            Spacer(Modifier.height(12.dp))
            if (product.tags.isNotEmpty()) {
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    product.tags.forEach { tagTitle ->
                        TagChip(title = tagTitle)
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
            Row {
                AmountInfo(amount = product.amount, modifier = Modifier.weight(1f))
                DateInfo(date = product.date, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    IdeaPlatformContestTheme {
        ProductCard(
            product = ProductUi(
                id = 1232,
                name = "iPhone 13",
                tags = listOf("Телефон", "Новый", "Распродажа", "Новый", "Распродажа"),
                amount = "15",
                date = "01.10.2021"
            ),
            onOpenDeleteProductDialog = {},
            onOpenChangingAmountDialog = { _, _ ->}
        )
    }
}