package com.example.cmp_mvi_template.feature.product.domain.usecase

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.core.domain.onError
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.repository.ProductRepository

class ToggleFavoriteProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product): ResultWrapper<Boolean, DataError.Local> {
        val isFavorite = repository.isProductFavorite(product.id)
        return isFavorite.onSuccess {
            if (it) {
                repository.removeFromFavorites(product.id)
            } else {
                repository.addToFavorites(product)
            }
        }.onError {
            ResultWrapper.Error(it)
        }
    }
}

