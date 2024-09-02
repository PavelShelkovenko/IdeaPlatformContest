package com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pavelshelkovenko.ideaplatformcontest.R
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.theme.IdeaPlatformContestTheme

@Composable
fun DeleteProductDialog(
    productId: Int,
    modifier: Modifier = Modifier,
    onDismissDialog: () -> Unit,
    onConfirmDeletingProduct: (Int) -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissDialog,
        modifier = modifier,
        text = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = stringResource(R.string.warning_icon_description)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.deleting_product_title),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = stringResource(R.string.deleting_product_dialog_info),)
            }
        },
        confirmButton = {
            Text(
                text = stringResource(R.string.confirmation_title),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        onConfirmDeletingProduct(productId)
                        onDismissDialog()
                    },
                color = MaterialTheme.colorScheme.tertiary,
            )
        },
        dismissButton = {
            Text(
                text = stringResource(R.string.decline_title),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.clickable { onDismissDialog() }
            )
        }
    )
}

@Preview
@Composable
fun DeleteProductDialogPreview() {
    IdeaPlatformContestTheme {
        DeleteProductDialog(
            productId = 0,
            onDismissDialog = {},
            onConfirmDeletingProduct = {}
        )
    }
}