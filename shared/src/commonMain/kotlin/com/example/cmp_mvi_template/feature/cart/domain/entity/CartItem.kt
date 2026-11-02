package com.example.cmp_mvi_template.feature.cart.domain.entity

import kotlin.math.roundToInt

data class CartItem(
    val id: Int,
    val title: String,
    val price: Double,
    val discountPercentage: Double,
    val thumbnail: String,
    val quantity: Int
) {
    val discountedUnitPrice: Double
        get() = if (discountPercentage > 0) {
            ((price * (1 - discountPercentage / 100)) * 100).roundToInt() / 100.0
        } else price

    val totalItemPrice: Double
        get() = ((discountedUnitPrice * quantity) * 100).roundToInt() / 100.0
}

data class CartSummary(
    val subtotal: Double,
    val discountTotal: Double,
    val finalTotal: Double,
    val totalItemCount: Int
)

