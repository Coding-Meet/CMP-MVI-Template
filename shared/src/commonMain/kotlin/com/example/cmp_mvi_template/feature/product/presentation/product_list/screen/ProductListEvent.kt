package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

sealed interface ProductListEvent {
    data object LoadProducts : ProductListEvent
    data object LoadMoreProducts : ProductListEvent
    data class NavigateToProductDetails(val productId: Int) : ProductListEvent
    data object ClearError : ProductListEvent
}

