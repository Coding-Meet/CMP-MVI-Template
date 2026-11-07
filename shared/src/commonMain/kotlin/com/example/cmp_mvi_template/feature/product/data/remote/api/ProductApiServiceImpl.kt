package com.example.cmp_mvi_template.feature.product.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

class ProductApiServiceImpl(
    private val httpClient: HttpClient
) : ProductApiService {

    override suspend fun getProductList(
        limit: Int,
        skip: Int,
        sortBy: String?,
        order: String?
    ): HttpResponse {
        return httpClient.get("products") {
            parameter("limit", limit)
            parameter("skip", skip)
            sortBy?.let { parameter("sortBy", it) }
            order?.let { parameter("order", it) }
        }
    }

    override suspend fun getProductById(id: Int): HttpResponse {
        return httpClient.get("products/$id")
    }

    override suspend fun searchProducts(
        query: String,
        limit: Int,
        skip: Int,
        sortBy: String?,
        order: String?
    ): HttpResponse {
        return httpClient.get("products/search") {
            parameter("q", query)
            parameter("limit", limit)
            parameter("skip", skip)
            sortBy?.let { parameter("sortBy", it) }
            order?.let { parameter("order", it) }
        }
    }

    override suspend fun getCategories(): HttpResponse {
        return httpClient.get("products/categories")
    }

    override suspend fun getProductsByCategory(
        category: String,
        limit: Int,
        skip: Int,
        sortBy: String?,
        order: String?
    ): HttpResponse {
        return httpClient.get("products/category/$category") {
            parameter("limit", limit)
            parameter("skip", skip)
            sortBy?.let { parameter("sortBy", it) }
            order?.let { parameter("order", it) }
        }
    }
}
