package com.example.cmp_mvi_template.feature.product.domain.usecase

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.product.domain.repository.ProductRepository

class CheckIfProductFavoriteUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): ResultWrapper<Boolean, DataError.Local> {
        return repository.isProductFavorite(productId)
    }
}

