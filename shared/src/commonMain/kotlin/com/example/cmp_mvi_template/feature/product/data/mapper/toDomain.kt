package com.example.cmp_mvi_template.feature.product.data.mapper

import com.example.cmp_mvi_template.feature.product.data.remote.dto.ProductDto
import com.example.cmp_mvi_template.feature.product.data.remote.dto.ProductListResponseDto
import com.example.cmp_mvi_template.feature.product.data.remote.dto.ReviewDto
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductEntity
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductListResponse
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductReview

fun ProductListResponseDto.toDomain(): ProductListResponse {
    return ProductListResponse(
        products = products.map { it.toDomain() },
        total = total,
        skip = skip,
        limit = limit
    )
}

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage ?: 0.0,
        rating = rating ?: 0.0,
        stock = stock ?: 0,
        tags = tags ?: emptyList(),
        brand = brand,
        warrantyInformation = warrantyInformation,
        shippingInformation = shippingInformation,
        availabilityStatus = availabilityStatus,
        returnPolicy = returnPolicy,
        minimumOrderQuantity = minimumOrderQuantity ?: 1,
        thumbnail = thumbnail,
        images = images ?: emptyList(),
        reviews = reviews?.map { it.toDomain() } ?: emptyList()
    )
}

fun ReviewDto.toDomain(): ProductReview {
    return ProductReview(
        rating = rating,
        comment = comment,
        date = date,
        reviewerName = reviewerName
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        thumbnail = thumbnail,
        images = images.joinToString(",")
    )
}

fun ProductEntity.toDomain(): Product {
    val imageList = if (images.isBlank()) emptyList() else images.split(",")
    return Product(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        tags = emptyList(),
        brand = brand,
        warrantyInformation = null,
        shippingInformation = null,
        availabilityStatus = null,
        returnPolicy = null,
        minimumOrderQuantity = 1,
        thumbnail = thumbnail,
        images = imageList.ifEmpty { listOf(thumbnail) },
        reviews = emptyList()
    )
}

