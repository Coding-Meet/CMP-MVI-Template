package com.example.cmp_mvi_template.feature.cart.presentation.screen

sealed interface CartEvent {
    data object LoadCart : CartEvent
    data class IncreaseQuantity(val productId: Int, val currentQuantity: Int) : CartEvent
    data class DecreaseQuantity(val productId: Int, val currentQuantity: Int) : CartEvent
    data class RemoveItem(val productId: Int) : CartEvent
    data object ClearCart : CartEvent
    data object Checkout : CartEvent
    data object DismissCheckoutDialog : CartEvent
    data class NavigateToProductDetails(val productId: Int) : CartEvent
}
