package com.example.cmp_mvi_template.feature.cart.domain.usecase

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.feature.cart.domain.repository.CartRepository

class UpdateCartQuantityUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(productId: Int, quantity: Int): EmptyResult<DataError.Local> {
        return repository.updateQuantity(productId, quantity)
    }
}

