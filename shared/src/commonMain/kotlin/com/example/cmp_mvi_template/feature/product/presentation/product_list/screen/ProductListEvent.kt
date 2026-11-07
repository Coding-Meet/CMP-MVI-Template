package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import com.example.cmp_mvi_template.feature.product.domain.entity.ProductSortOption

sealed interface ProductListEvent {
    data object LoadProducts : ProductListEvent
    data object LoadMoreProducts : ProductListEvent
    data class SelectCategory(val categorySlug: String?) : ProductListEvent
    data class UpdateSearchQuery(val query: String) : ProductListEvent
    data class SelectSortOption(val sortOption: ProductSortOption) : ProductListEvent
    data class ToggleSortBottomSheet(val show: Boolean) : ProductListEvent
    data class ToggleSearchBar(val visible: Boolean) : ProductListEvent
    data class NavigateToProductDetails(val productId: Int) : ProductListEvent
    data object ClearError : ProductListEvent
}
