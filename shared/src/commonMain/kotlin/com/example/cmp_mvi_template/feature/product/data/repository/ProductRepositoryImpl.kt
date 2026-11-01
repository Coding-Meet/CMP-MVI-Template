package com.example.cmp_mvi_template.feature.product.data.repository

import androidx.sqlite.SQLiteException
import com.example.cmp_mvi_template.core.data.local.dao.ProductDao
import com.example.cmp_mvi_template.core.data.network.safeCall
import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.core.domain.map
import com.example.cmp_mvi_template.feature.product.data.mapper.toDomain
import com.example.cmp_mvi_template.feature.product.data.mapper.toEntity
import com.example.cmp_mvi_template.feature.product.data.remote.api.ProductApiService
import com.example.cmp_mvi_template.feature.product.data.remote.dto.ProductDto
import com.example.cmp_mvi_template.feature.product.data.remote.dto.ProductListResponseDto
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductListResponse
import com.example.cmp_mvi_template.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val apiService: ProductApiService,
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun getProductList(
        limit: Int,
        skip: Int
    ): ResultWrapper<ProductListResponse, DataError> {
        return safeCall<ProductListResponseDto> {
            apiService.getProductList(limit, skip)
        }.map { it.toDomain() }
    }

    override suspend fun getProductById(id: Int): ResultWrapper<Product, DataError> {
        return safeCall<ProductDto> {
            apiService.getProductById(id)
        }.map { it.toDomain() }
    }

    override suspend fun searchProducts(
        query: String,
        limit: Int,
        skip: Int
    ): ResultWrapper<ProductListResponse, DataError> {
        return safeCall<ProductListResponseDto> {
            apiService.searchProducts(query, limit, skip)
        }.map { it.toDomain() }
    }

    override fun getFavoriteProducts(): Flow<List<Product>> {
        return productDao.getAllFavoriteProducts()
            .map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun addToFavorites(product: Product): EmptyResult<DataError.Local> {
        return try {
            productDao.insertProduct(product.toEntity())
            ResultWrapper.Success(Unit)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun removeFromFavorites(productId: Int): EmptyResult<DataError.Local> {
        return try {
            productDao.deleteProductById(productId)
            ResultWrapper.Success(Unit)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun isProductFavorite(productId: Int): ResultWrapper<Boolean, DataError.Local> {
        return try {
            val result = productDao.isProductFavorite(productId)
            ResultWrapper.Success(result)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }
}

