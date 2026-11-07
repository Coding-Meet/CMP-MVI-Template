package com.example.cmp_mvi_template.feature.cart.domain.usecase

import com.example.cmp_mvi_template.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartCountUseCase(
    private val repository: CartRepository
) {
    operator fun invoke(): Flow<Int> {
        return repository.getCartTotalCount()
    }
}

