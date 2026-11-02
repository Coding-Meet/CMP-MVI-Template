package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.domain.Paginator
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.AppLogger
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductSortOption
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetCategoriesUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetProductListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductListState())
    val state = _state
        .onStart {
            loadCategories()
            handleEvent(ProductListEvent.LoadProducts)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _effect = MutableSharedFlow<ProductListEffect>()
    val effect = _effect.asSharedFlow()

    private var searchJob: Job? = null
    private var currentSkip = 0
    private val limit = 20

    private val paginator = Paginator(
        initialKey = 0,
        onLoadUpdated = { isLoadingMore ->
            _state.update { it.copy(isLoadingMore = isLoadingMore) }
        },
        onRequest = { skip ->
            val currentState = _state.value
            AppLogger.d("ProductListViewModel") {
                "onRequest skip: $skip, cat: ${currentState.selectedCategory}, search: ${currentState.searchQuery}"
            }
            getProductListUseCase(
                limit = limit,
                skip = skip,
                category = currentState.selectedCategory,
                searchQuery = currentState.searchQuery.ifBlank { null },
                sortBy = currentState.selectedSort.sortBy,
                order = currentState.selectedSort.order
            )
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
            is ProductListEvent.SelectCategory -> selectCategory(event.categorySlug)
            is ProductListEvent.UpdateSearchQuery -> updateSearchQuery(event.query)
            is ProductListEvent.SelectSortOption -> selectSortOption(event.sortOption)
            is ProductListEvent.ToggleSortBottomSheet -> toggleSortBottomSheet(event.show)
            is ProductListEvent.ToggleSearchBar -> toggleSearchBar(event.visible)
            is ProductListEvent.NavigateToProductDetails -> navigateToDetails(event.productId)
            ProductListEvent.ClearError -> clearError()
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().onSuccess { categories ->
                _state.update { it.copy(categories = categories) }
            }
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, productList = emptyList()) }
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

    private fun selectCategory(categorySlug: String?) {
        if (_state.value.selectedCategory == categorySlug) return
        _state.update { it.copy(selectedCategory = categorySlug, searchQuery = "") }
        loadProducts()
    }

    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(350L) // Debounce
            loadProducts()
        }
    }

    private fun selectSortOption(sortOption: ProductSortOption) {
        if (_state.value.selectedSort == sortOption) {
            _state.update { it.copy(showSortBottomSheet = false) }
            return
        }
        _state.update { it.copy(selectedSort = sortOption, showSortBottomSheet = false) }
        loadProducts()
    }

    private fun toggleSortBottomSheet(show: Boolean) {
        _state.update { it.copy(showSortBottomSheet = show) }
    }

    private fun toggleSearchBar(visible: Boolean) {
        _state.update {
            it.copy(
                isSearchBarVisible = visible,
                searchQuery = if (!visible) "" else it.searchQuery
            )
        }
        if (!visible) {
            loadProducts()
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
