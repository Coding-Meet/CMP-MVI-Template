package com.example.cmp_mvi_template.feature.product.domain.usecase

import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteProductsUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getFavoriteProducts()
    }
}

