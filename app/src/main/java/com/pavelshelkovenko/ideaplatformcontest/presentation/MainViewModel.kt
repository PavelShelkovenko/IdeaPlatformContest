package com.pavelshelkovenko.ideaplatformcontest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelshelkovenko.ideaplatformcontest.domain.ProductRepository
import com.pavelshelkovenko.ideaplatformcontest.presentation.mapper.PresentationMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ProductRepository,
    private val mapper: PresentationMapper,
) : ViewModel() {

    var screenState: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState())
        private set

    private val searchQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    init {
        listenToSearchQuery()
    }

    private fun listenToSearchQuery() {
        searchQueryFlow
            .onStart { emit("") }
            .distinctUntilChanged()
            .debounce(500)
            .mapLatest(::search)
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.ChangeProductAmount -> {
                viewModelScope.launch {
                    repository.changeProductAmount(
                        productId = event.productId,
                        newAmount = event.newAmount
                    )
                }
            }

            is MainScreenEvent.DeleteProductById -> {
                viewModelScope.launch {
                    repository.deleteProductById(event.productId)
                }
            }

            is MainScreenEvent.GetProducts -> {
                searchQueryFlow.tryEmit(value = event.query)
                screenState.value = screenState.value.copy(
                    searchQuery = event.query
                )
            }

            MainScreenEvent.ClearSearchQuery -> {
                searchQueryFlow.tryEmit(value = "")
                screenState.value = screenState.value.copy(
                    searchQuery = ""
                )
            }

            MainScreenEvent.CloseChangingAmountDialog -> {
                screenState.value = screenState.value.copy(
                    dialogState = screenState.value.dialogState.copy(
                        isChangingAmountDialogOpen = false,
                        changingProductId = null,
                    )
                )
            }
            MainScreenEvent.CloseDeletingDialog -> {
                screenState.value = screenState.value.copy(
                    dialogState = screenState.value.dialogState.copy(
                        isDeletingDialogOpen = false,
                        deletingProductId = null,
                    )
                )
            }
            is MainScreenEvent.OpenChangingAmountDialog -> {
                screenState.value = screenState.value.copy(
                    dialogState = screenState.value.dialogState.copy(
                        isChangingAmountDialogOpen = true,
                        initialProductAmount = event.initialProductAmount,
                        changingProductId = event.changingProductId
                    )
                )
            }
            is MainScreenEvent.OpenDeletingDialog -> {
                screenState.value = screenState.value.copy(
                    dialogState = screenState.value.dialogState.copy(
                        isDeletingDialogOpen = true,
                        deletingProductId = event.productId
                    )
                )
            }
        }
    }

    private suspend fun search(query: String) {
        repository
            .getProductsFlow(query)
            .collect { products ->
                screenState.value = screenState.value.copy(
                    products = products.map { mapper.mapProductDomainToUi(it) },
                )
            }
    }
}