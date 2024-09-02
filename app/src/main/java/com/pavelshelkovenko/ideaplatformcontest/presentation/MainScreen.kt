package com.pavelshelkovenko.ideaplatformcontest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pavelshelkovenko.ideaplatformcontest.R
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables.ChangeAmountDialog
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables.DeleteProductDialog
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables.ProductCard
import com.pavelshelkovenko.ideaplatformcontest.presentation.ui.composables.SearchField
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
) {
    val viewModel: MainViewModel = koinViewModel()
    val screenState = viewModel.screenState.collectAsState()

    MainScreenContent(
        state = screenState,
        modifier = modifier
            .padding(innerPadding),
        onClearQuery = {
            viewModel.onEvent(MainScreenEvent.ClearSearchQuery)
        },
        onQueryChanged = { newQuery ->
            viewModel.onEvent(MainScreenEvent.GetProducts(query = newQuery))
        },
        onOpenChangingAmountDialog = { productId, initialAmount ->
            viewModel.onEvent(
                MainScreenEvent.OpenChangingAmountDialog(
                    initialProductAmount = initialAmount,
                    changingProductId = productId
                )
            )
        },
        onDismissChangeAmountDialog = {
            viewModel.onEvent(MainScreenEvent.CloseChangingAmountDialog)
        },
        onConfirmChangingAmount = { productId, newAmount ->
            viewModel.onEvent(
                MainScreenEvent.ChangeProductAmount(
                    productId = productId,
                    newAmount = newAmount,
                )
            )
        },
        onOpenDeleteProductDialog = { productId ->
            viewModel.onEvent(MainScreenEvent.OpenDeletingDialog(productId = productId))
        },
        onDismissDeleteProductDialog = {
            viewModel.onEvent(MainScreenEvent.CloseDeletingDialog)
        },
        onConfirmDeleteProduct = { productId ->
            viewModel.onEvent(MainScreenEvent.DeleteProductById(productId = productId))
        }
    )

}

@Composable
fun MainScreenContent(
    state: State<MainScreenState>,
    modifier: Modifier = Modifier,
    onClearQuery: () -> Unit,
    onQueryChanged: (String) -> Unit,
    onOpenChangingAmountDialog: (Int, Int) -> Unit,
    onDismissChangeAmountDialog: () -> Unit,
    onConfirmChangingAmount: (Int, Int) -> Unit,
    onOpenDeleteProductDialog: (Int) -> Unit,
    onDismissDeleteProductDialog: () -> Unit,
    onConfirmDeleteProduct: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = 32.dp, bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.main_screen_title),
                style = MaterialTheme.typography.titleLarge,
            )
        }
        SearchField(
            query = state.value.searchQuery,
            onQueryChange = { newQuery ->
                onQueryChanged(newQuery)
            },
            onClearQuery = { onClearQuery() },
            modifier = Modifier.padding(8.dp)
        )
        if (state.value.isError) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.error_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        if (state.value.isLoading) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (!state.value.isError && !state.value.isLoading) {
            LazyColumn(
                modifier = Modifier.padding(8.dp)
            ) {
                items(
                    items = state.value.products,
                    key = { product -> product.id }
                ) { product ->
                    ProductCard(
                        product = product,
                        onOpenChangingAmountDialog = { productId, initialAmount ->
                            onOpenChangingAmountDialog(productId, initialAmount)
                        },
                        onOpenDeleteProductDialog = { productId ->
                            onOpenDeleteProductDialog(productId)
                        }
                    )
                }
            }
        }
    }

    if (state.value.dialogState.isChangingAmountDialogOpen && state.value.dialogState.changingProductId != null) {
        ChangeAmountDialog(
            onDismissDialog = onDismissChangeAmountDialog,
            initialAmount = state.value.dialogState.initialProductAmount,
            onConfirmChangingAmount = onConfirmChangingAmount,
            changingProductId = state.value.dialogState.changingProductId!!
        )
    }

    if (state.value.dialogState.isDeletingDialogOpen && state.value.dialogState.deletingProductId != null) {
        DeleteProductDialog(
            onDismissDialog = onDismissDeleteProductDialog,
            onConfirmDeletingProduct = onConfirmDeleteProduct,
            productId = state.value.dialogState.deletingProductId!!
        )
    }
}
