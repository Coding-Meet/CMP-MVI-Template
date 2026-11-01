package com.example.cmp_mvi_template.feature.product.data.remote.api

import io.ktor.client.statement.HttpResponse

interface ProductApiService {
    suspend fun getProductList(limit: Int, skip: Int): HttpResponse
    suspend fun getProductById(id: Int): HttpResponse
    suspend fun searchProducts(query: String, limit: Int, skip: Int): HttpResponse
}

