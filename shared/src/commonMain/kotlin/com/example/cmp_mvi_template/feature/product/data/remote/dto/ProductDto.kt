package com.example.cmp_mvi_template.feature.product.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponseDto(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double? = 0.0,
    val rating: Double? = 0.0,
    val stock: Int? = 0,
    val tags: List<String>? = emptyList(),
    val brand: String? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val returnPolicy: String? = null,
    val minimumOrderQuantity: Int? = null,
    val thumbnail: String,
    val images: List<String>? = emptyList(),
    val reviews: List<ReviewDto>? = emptyList()
)

@Serializable
data class ReviewDto(
    val rating: Int = 0,
    val comment: String = "",
    val date: String = "",
    val reviewerName: String = "",
    val reviewerEmail: String? = null
)

@Serializable
data class CategoryDto(
    val slug: String,
    val name: String,
    val url: String = ""
)
