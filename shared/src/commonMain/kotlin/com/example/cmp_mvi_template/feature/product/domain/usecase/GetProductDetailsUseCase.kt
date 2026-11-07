package com.example.cmp_mvi_template.feature.product.domain.usecase

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.repository.ProductRepository

class GetProductDetailsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): ResultWrapper<Product, DataError> {
        return repository.getProductById(id)
    }
}

