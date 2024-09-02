package com.pavelshelkovenko.ideaplatformcontest.presentation

sealed interface MainScreenEvent {

    data class DeleteProductById(val productId: Int) : MainScreenEvent

    data class ChangeProductAmount(val productId: Int, val newAmount: Int) : MainScreenEvent

    data class GetProducts(val query: String): MainScreenEvent

    data object ClearSearchQuery: MainScreenEvent

    data class OpenDeletingDialog(val productId: Int): MainScreenEvent

    data object CloseDeletingDialog: MainScreenEvent

    data class OpenChangingAmountDialog(
        val initialProductAmount: Int,
        val changingProductId: Int,
    ): MainScreenEvent

    data object CloseChangingAmountDialog: MainScreenEvent
}