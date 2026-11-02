package com.example.cmp_mvi_template.feature.product.domain.repository

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductCategory
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductListResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductList(
        limit: Int = 20,
        skip: Int = 0,
        sortBy: String? = null,
        order: String? = null
    ): ResultWrapper<ProductListResponse, DataError>

    suspend fun getProductById(id: Int): ResultWrapper<Product, DataError>

    suspend fun searchProducts(
        query: String,
        limit: Int = 20,
        skip: Int = 0,
        sortBy: String? = null,
        order: String? = null
    ): ResultWrapper<ProductListResponse, DataError>

    suspend fun getCategories(): ResultWrapper<List<ProductCategory>, DataError>

    suspend fun getProductsByCategory(
        category: String,
        limit: Int = 20,
        skip: Int = 0,
        sortBy: String? = null,
        order: String? = null
    ): ResultWrapper<ProductListResponse, DataError>

    // Favorites / Saved
    fun getFavoriteProducts(): Flow<List<Product>>
    suspend fun addToFavorites(product: Product): EmptyResult<DataError.Local>
    suspend fun removeFromFavorites(productId: Int): EmptyResult<DataError.Local>
    suspend fun isProductFavorite(productId: Int): ResultWrapper<Boolean, DataError.Local>
}
