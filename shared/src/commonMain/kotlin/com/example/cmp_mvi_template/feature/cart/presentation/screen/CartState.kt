package com.example.cmp_mvi_template.feature.cart.presentation.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartItem
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartSummary
import kotlin.math.roundToInt

data class CartState(
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val showCheckoutSuccessDialog: Boolean = false,
    val error: UiText? = null
) {
    val summary: CartSummary
        get() {
            var subtotal = 0.0
            var finalTotal = 0.0
            var totalCount = 0

            cartItems.forEach { item ->
                subtotal += item.price * item.quantity
                finalTotal += item.totalItemPrice
                totalCount += item.quantity
            }

            val discountTotal = subtotal - finalTotal
            return CartSummary(
                subtotal = (subtotal * 100).roundToInt() / 100.0,
                discountTotal = (discountTotal * 100).roundToInt() / 100.0,
                finalTotal = (finalTotal * 100).roundToInt() / 100.0,
                totalItemCount = totalCount
            )
        }
}

