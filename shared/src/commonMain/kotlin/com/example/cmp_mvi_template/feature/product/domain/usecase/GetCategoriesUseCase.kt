package com.example.cmp_mvi_template.feature.product.domain.usecase

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductCategory
import com.example.cmp_mvi_template.feature.product.domain.repository.ProductRepository

class GetCategoriesUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): ResultWrapper<List<ProductCategory>, DataError> {
        return repository.getCategories()
    }
}

