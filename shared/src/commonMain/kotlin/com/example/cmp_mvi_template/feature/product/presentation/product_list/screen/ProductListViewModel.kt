package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductSortOption
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetCategoriesUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetProductListUseCase
import com.example.cmp_mvi_template.feature.product.presentation.product_list.paging.ProductPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private data class PagingKey(
    val category: String?,
    val searchQuery: String,
    val sortOption: ProductSortOption,
    val refreshTrigger: Long
)

class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductListState())
    val state = _state
        .onStart {
            loadCategories()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _effect = MutableSharedFlow<ProductListEffect>()
    val effect = _effect.asSharedFlow()

    private var searchJob: Job? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    val productPagingFlow: Flow<PagingData<Product>> = _state
        .map { s ->
            PagingKey(
                category = s.selectedCategory,
                searchQuery = s.activeSearchQuery,
                sortOption = s.selectedSort,
                refreshTrigger = s.refreshTrigger
            )
        }
        .distinctUntilChanged()
        .flatMapLatest { key ->
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20
                )
            ) {
                ProductPagingSource(
                    getProductListUseCase = getProductListUseCase,
                    category = key.category,
                    searchQuery = key.searchQuery,
                    sortOption = key.sortOption
                )
            }.flow
        }
        .cachedIn(viewModelScope)

    fun handleEvent(event: ProductListEvent) {
        when (event) {
            ProductListEvent.LoadProducts -> refreshProducts()
            ProductListEvent.LoadMoreProducts -> { /* Handled automatically by Paging 3 */ }
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

    private fun refreshProducts() {
        _state.update { it.copy(refreshTrigger = it.refreshTrigger + 1) }
    }

    private fun selectCategory(categorySlug: String?) {
        if (_state.value.selectedCategory == categorySlug) return
        searchJob?.cancel()
        _state.update {
            it.copy(
                selectedCategory = categorySlug,
                searchQuery = "",
                activeSearchQuery = ""
            )
        }
    }

    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(350L) // Debounce
            _state.update { it.copy(activeSearchQuery = query) }
        }
    }

    private fun selectSortOption(sortOption: ProductSortOption) {
        if (_state.value.selectedSort == sortOption) {
            _state.update { it.copy(showSortBottomSheet = false) }
            return
        }
        _state.update { it.copy(selectedSort = sortOption, showSortBottomSheet = false) }
    }

    private fun toggleSortBottomSheet(show: Boolean) {
        _state.update { it.copy(showSortBottomSheet = show) }
    }

    private fun toggleSearchBar(visible: Boolean) {
        searchJob?.cancel()
        _state.update {
            it.copy(
                isSearchBarVisible = visible,
                searchQuery = if (!visible) "" else it.searchQuery,
                activeSearchQuery = if (!visible) "" else it.activeSearchQuery
            )
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
