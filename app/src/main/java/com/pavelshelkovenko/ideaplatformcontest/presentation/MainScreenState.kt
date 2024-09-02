package com.pavelshelkovenko.ideaplatformcontest.presentation

import com.pavelshelkovenko.ideaplatformcontest.presentation.models.ProductUi

data class MainScreenState(
    val searchQuery: String = "",
    val products: List<ProductUi> = emptyList(),
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val dialogState: DialogState = DialogState(),
)


data class DialogState(
    val isDeletingDialogOpen: Boolean = false,
    val isChangingAmountDialogOpen: Boolean = false,
    val initialProductAmount: Int = 0,
    val changingProductId: Int? = null,
    val deletingProductId: Int? = null,
)