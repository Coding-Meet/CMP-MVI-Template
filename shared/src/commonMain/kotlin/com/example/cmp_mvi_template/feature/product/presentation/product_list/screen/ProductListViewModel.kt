package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.domain.Paginator
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.AppLogger
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetProductListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductListState())
    val state = _state
        .onStart {
            handleEvent(ProductListEvent.LoadProducts)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _effect = MutableSharedFlow<ProductListEffect>()
    val effect = _effect.asSharedFlow()

    private var currentSkip = 0
    private val limit = 20
    private val paginator = Paginator(
        initialKey = 0,
        onLoadUpdated = { isLoadingMore ->
            _state.update { it.copy(isLoadingMore = isLoadingMore) }
        },
        onRequest = { skip ->
            AppLogger.d("ProductListViewModel") {
                "onRequest skip: $skip"
            }
            getProductListUseCase(limit, skip)
        },
        getNextKey = { currentSkip, _ ->
            currentSkip + limit
        },
        onError = { error ->
            _state.update {
                it.copy(error = error.toUiText())
            }
        },
        onSuccess = { response, newSkip ->
            AppLogger.d("ProductListViewModel") {
                "onSuccess total: ${response.total}, newSkip: $newSkip"
            }
            currentSkip = newSkip
            _state.update {
                it.copy(
                    productList = it.productList + response.products,
                    hasMoreData = response.skip + response.limit < response.total
                )
            }
        },
        endReached = { _, response ->
            response.skip + response.limit >= response.total
        }
    )

    fun handleEvent(event: ProductListEvent) {
        when (event) {
            ProductListEvent.LoadProducts -> loadProducts()
            ProductListEvent.LoadMoreProducts -> loadMoreProducts()
            is ProductListEvent.NavigateToProductDetails -> navigateToDetails(event.productId)
            ProductListEvent.ClearError -> clearError()
        }
    }

    private fun loadProducts() {
        if (_state.value.isLoading) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            paginator.reset()
            currentSkip = 0
            paginator.loadNextItems()
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun loadMoreProducts() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private fun navigateToDetails(productId: Int) {
        viewModelScope.launch {
            _effect.emit(ProductListEffect.NavigateToDetails(productId))
        }
    }

    private fun clearError() {
        _state.update { it.copy(error = null) }
    }
}

