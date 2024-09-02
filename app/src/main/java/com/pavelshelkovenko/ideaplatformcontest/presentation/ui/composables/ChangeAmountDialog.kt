package com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pavelshelkovenko.ideaplatformcontest.R
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.theme.IdeaPlatformContestTheme

@Composable
fun ChangeAmountDialog(
    initialAmount: Int,
    changingProductId: Int,
    modifier: Modifier = Modifier,
    onDismissDialog: () -> Unit,
    onConfirmChangingAmount: (Int, Int) -> Unit,
) {

    var amount by rememberSaveable {
        mutableIntStateOf(initialAmount)
    }

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
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(R.string.settings_icon_description)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.changing_amount_dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    IconButton(onClick = { amount-- }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.remove_circle_outline_24
                            ),
                            contentDescription = stringResource(R.string.decrease_button_description),
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = amount.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    IconButton(onClick = { amount++ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.add_circle_outline_24
                            ),
                            contentDescription = stringResource(R.string.increase_button_description),
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Text(
                text = stringResource(R.string.confirm),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        onConfirmChangingAmount(changingProductId, amount)
                        onDismissDialog()
                    },
                color = MaterialTheme.colorScheme.tertiary,
            )
        },
        dismissButton = {
            Text(
                text = stringResource(R.string.cancel),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.clickable { onDismissDialog() }
            )
        }
    )
}

@Preview
@Composable
fun ChangeAmountDialogPreview() {
    IdeaPlatformContestTheme {
        ChangeAmountDialog(
            onDismissDialog = {},
            initialAmount = 30,
            onConfirmChangingAmount = { _, _ ->},
            changingProductId = 0
        )
    }
}