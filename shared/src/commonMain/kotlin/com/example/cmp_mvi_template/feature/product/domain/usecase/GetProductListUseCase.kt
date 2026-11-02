package com.example.cmp_mvi_template.feature.product.domain.usecase

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductListResponse
import com.example.cmp_mvi_template.feature.product.domain.repository.ProductRepository

class GetProductListUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(
        limit: Int = 20,
        skip: Int = 0,
        category: String? = null,
        searchQuery: String? = null,
        sortBy: String? = null,
        order: String? = null
    ): ResultWrapper<ProductListResponse, DataError> {
        return when {
            !searchQuery.isNullOrBlank() -> repository.searchProducts(searchQuery.trim(), limit, skip, sortBy, order)
            !category.isNullOrBlank() -> repository.getProductsByCategory(category, limit, skip, sortBy, order)
            else -> repository.getProductList(limit, skip, sortBy, order)
        }
    }
}
