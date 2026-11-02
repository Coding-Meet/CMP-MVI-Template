package com.example.cmp_mvi_template.feature.product.presentation.product_details.screen

sealed interface ProductDetailsEvent {
    data class LoadProductDetails(val productId: Int) : ProductDetailsEvent
    data object ToggleFavorite : ProductDetailsEvent
    data object AddToCart : ProductDetailsEvent
    data object NavigateBack : ProductDetailsEvent
    data object ClearError : ProductDetailsEvent
}
