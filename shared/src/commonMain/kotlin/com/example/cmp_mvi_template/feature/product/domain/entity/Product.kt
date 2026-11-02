package com.example.cmp_mvi_template.feature.product.domain.entity

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String?,
    val warrantyInformation: String?,
    val shippingInformation: String?,
    val availabilityStatus: String?,
    val returnPolicy: String?,
    val minimumOrderQuantity: Int,
    val thumbnail: String,
    val images: List<String>,
    val reviews: List<ProductReview>
)

data class ProductReview(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String
)

data class ProductListResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class ProductCategory(
    val slug: String,
    val name: String
)

enum class ProductSortOption(val displayName: String, val sortBy: String?, val order: String?) {
    DEFAULT("Default", null, null),
    PRICE_LOW_TO_HIGH("Price: Low to High", "price", "asc"),
    PRICE_HIGH_TO_LOW("Price: High to Low", "price", "desc"),
    RATING_HIGH_TO_LOW("Rating: Highest First", "rating", "desc"),
    TITLE_A_TO_Z("Title: A to Z", "title", "asc"),
    TITLE_Z_TO_A("Title: Z to A", "title", "desc")
}
