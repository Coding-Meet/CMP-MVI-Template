package com.example.cmp_mvi_template.feature.cart.domain.repository

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    fun getCartTotalCount(): Flow<Int>
    suspend fun addToCart(cartItem: CartItem): EmptyResult<DataError.Local>
    suspend fun updateQuantity(productId: Int, quantity: Int): EmptyResult<DataError.Local>
    suspend fun removeFromCart(productId: Int): EmptyResult<DataError.Local>
    suspend fun clearCart(): EmptyResult<DataError.Local>
}

