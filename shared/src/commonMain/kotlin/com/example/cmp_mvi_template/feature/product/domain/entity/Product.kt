package com.example.cmp_mvi_template.feature.product.domain.entity

data class Product(
    val id: Int,
    val title: String,
    val description: String = "",
    val category: String = "",
    val price: Double = 0.0,
    val discountPercentage: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val tags: List<String> = emptyList(),
    val brand: String? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val returnPolicy: String? = null,
    val minimumOrderQuantity: Int = 1,
    val thumbnail: String = "",
    val images: List<String> = emptyList(),
    val reviews: List<ProductReview> = emptyList()
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
