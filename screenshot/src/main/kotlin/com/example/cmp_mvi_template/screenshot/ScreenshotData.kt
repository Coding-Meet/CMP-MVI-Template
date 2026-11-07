package com.example.cmp_mvi_template.screenshot

import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartItem
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartSummary
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductCategory
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductReview

internal val sampleCategories = listOf(
    ProductCategory(slug = "beauty", name = "Beauty"),
    ProductCategory(slug = "fragrances", name = "Fragrances"),
    ProductCategory(slug = "furniture", name = "Furniture"),
    ProductCategory(slug = "groceries", name = "Groceries"),
    ProductCategory(slug = "home-decoration", name = "Home Decoration"),
    ProductCategory(slug = "laptops", name = "Laptops")
)

internal val sampleProducts = listOf(
    Product(
        id = 1,
        title = "Essence Mascara Lash Princess",
        description = "The Essence Mascara Lash Princess False Lash Effect Mascara is a popular mascara known for creating voluminous and dramatic lashes.",
        category = "beauty",
        price = 9.99,
        discountPercentage = 7.17,
        rating = 4.94,
        stock = 5,
        tags = listOf("beauty", "mascara"),
        brand = "Essence",
        warrantyInformation = "1 month warranty",
        shippingInformation = "Ships in 1 month",
        availabilityStatus = "Low Stock",
        returnPolicy = "30 days return policy",
        thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png",
        images = listOf(
            "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png"
        ),
        reviews = listOf(
            ProductReview(rating = 5, comment = "Great mascara, makes lashes pop!", date = "2024-05-23", reviewerName = "Eleanor"),
            ProductReview(rating = 4, comment = "Good value for money.", date = "2024-05-20", reviewerName = "Lucas")
        )
    ),
    Product(
        id = 2,
        title = "Eyeshadow Palette with Mirror",
        description = "The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks.",
        category = "beauty",
        price = 19.99,
        discountPercentage = 5.5,
        rating = 4.5,
        stock = 44,
        tags = listOf("beauty", "eyeshadow"),
        brand = "Glamour Beauty",
        warrantyInformation = "1 year warranty",
        shippingInformation = "Ships in 2 weeks",
        availabilityStatus = "In Stock",
        returnPolicy = "30 days return policy",
        thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Eyeshadow%20Palette%20with%20Mirror/thumbnail.png",
        images = listOf(
            "https://cdn.dummyjson.com/products/images/beauty/Eyeshadow%20Palette%20with%20Mirror/1.png"
        ),
        reviews = listOf(
            ProductReview(rating = 5, comment = "Highly pigmented and long lasting!", date = "2024-05-18", reviewerName = "Sophia")
        )
    ),
    Product(
        id = 3,
        title = "Powder Canister",
        description = "The Powder Canister is a finely milled setting powder designed to set makeup and control shine all day long.",
        category = "beauty",
        price = 14.99,
        discountPercentage = 10.0,
        rating = 4.25,
        stock = 12,
        tags = listOf("beauty", "face powder"),
        brand = "Velvet Touch",
        warrantyInformation = "2 years warranty",
        shippingInformation = "Ships in 3-5 business days",
        availabilityStatus = "In Stock",
        returnPolicy = "60 days return policy",
        thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Powder%20Canister/thumbnail.png",
        images = listOf(
            "https://cdn.dummyjson.com/products/images/beauty/Powder%20Canister/1.png"
        ),
        reviews = listOf(
            ProductReview(rating = 4, comment = "Keeps makeup matte without drying.", date = "2024-05-10", reviewerName = "Olivia")
        )
    ),
    Product(
        id = 4,
        title = "Red Lipstick Matte Finish",
        description = "The Red Lipstick is a classic bold red matte lipstick with rich pigment and comfortable long wear.",
        category = "beauty",
        price = 12.99,
        discountPercentage = 15.0,
        rating = 4.7,
        stock = 25,
        tags = listOf("beauty", "lipstick"),
        brand = "Chic Cosmetics",
        warrantyInformation = "No warranty",
        shippingInformation = "Ships overnight",
        availabilityStatus = "In Stock",
        returnPolicy = "No returns",
        thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Red%20Lipstick/thumbnail.png",
        images = listOf(
            "https://cdn.dummyjson.com/products/images/beauty/Red%20Lipstick/1.png"
        ),
        reviews = listOf(
            ProductReview(rating = 5, comment = "Best red lipstick ever!", date = "2024-05-15", reviewerName = "Mia")
        )
    ),
    Product(
        id = 5,
        title = "Calvin Klein CK One",
        description = "CK One by Calvin Klein is an iconic unisex fragrance featuring clean notes of green tea, bergamot, and cardamom.",
        category = "fragrances",
        price = 49.99,
        discountPercentage = 8.0,
        rating = 4.85,
        stock = 18,
        tags = listOf("fragrances", "perfumes"),
        brand = "Calvin Klein",
        warrantyInformation = "5 years warranty",
        shippingInformation = "Ships in 1-2 business days",
        availabilityStatus = "In Stock",
        returnPolicy = "14 days return policy",
        thumbnail = "https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/thumbnail.webp",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/1.webp"
        ),
        reviews = listOf(
            ProductReview(rating = 5, comment = "Timeless scent, very fresh.", date = "2024-05-12", reviewerName = "Liam")
        )
    ),
    Product(
        id = 6,
        title = "Chanel Coco Noir Eau De",
        description = "Coco Noir by Chanel is an elegant and mysterious fragrance, featuring notes of grapefruit, rose, and sandalwood.",
        category = "fragrances",
        price = 129.99,
        discountPercentage = 12.5,
        rating = 4.95,
        stock = 8,
        tags = listOf("fragrances", "perfumes", "luxury"),
        brand = "Chanel",
        warrantyInformation = "3 years warranty",
        shippingInformation = "Ships in 2-3 business days",
        availabilityStatus = "In Stock",
        returnPolicy = "30 days return policy",
        thumbnail = "https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/thumbnail.webp",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/1.webp"
        ),
        reviews = listOf(
            ProductReview(rating = 5, comment = "Absolutely exquisite perfume.", date = "2024-05-22", reviewerName = "Charlotte")
        )
    )
)

internal val samplePerfumeProducts = listOf(
    sampleProducts[4], // Calvin Klein CK One
    sampleProducts[5], // Chanel Coco Noir Eau De
    Product(
        id = 7,
        title = "Dior J'adore",
        description = "J'adore by Dior is a luxurious and floral fragrance, known for its blend of ylang-ylang, rose, and jasmine.",
        category = "fragrances",
        price = 89.99,
        discountPercentage = 10.0,
        rating = 4.92,
        stock = 14,
        tags = listOf("fragrances", "perfumes", "luxury"),
        brand = "Dior",
        warrantyInformation = "2 years warranty",
        shippingInformation = "Ships in 1-2 business days",
        availabilityStatus = "In Stock",
        returnPolicy = "30 days return policy",
        thumbnail = "https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/thumbnail.webp",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/1.webp"
        ),
        reviews = listOf(
            ProductReview(rating = 5, comment = "Signature scent, lasts all day!", date = "2024-05-15", reviewerName = "Alexander")
        )
    ),
    Product(
        id = 8,
        title = "Gucci Bloom Eau de",
        description = "Capturing the spirit of contemporary, diverse, and authentic women, Gucci Bloom notes include natural tuberose and jasmine.",
        category = "fragrances",
        price = 79.99,
        discountPercentage = 15.0,
        rating = 4.78,
        stock = 20,
        tags = listOf("fragrances", "perfumes"),
        brand = "Gucci",
        warrantyInformation = "1 year warranty",
        shippingInformation = "Ships in 2-4 business days",
        availabilityStatus = "In Stock",
        returnPolicy = "30 days return policy",
        thumbnail = "https://cdn.dummyjson.com/product-images/fragrances/gucci-bloom-eau-de/thumbnail.webp",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/fragrances/gucci-bloom-eau-de/1.webp"
        ),
        reviews = listOf(
            ProductReview(rating = 5, comment = "Rich white floral scent.", date = "2024-05-11", reviewerName = "Isabella")
        )
    )
)

internal val sampleSelectedProduct: Product = sampleProducts.first()

internal val sampleSearchProducts: List<Product> = samplePerfumeProducts

internal val sampleFavoriteProducts: List<Product> = listOf(sampleProducts[0], sampleProducts[1], sampleProducts[4])

internal val sampleCartItems: List<CartItem> = listOf(
    CartItem(
        id = 1,
        title = "Essence Mascara Lash Princess",
        price = 9.99,
        discountPercentage = 7.17,
        thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png",
        quantity = 2
    ),
    CartItem(
        id = 2,
        title = "Eyeshadow Palette with Mirror",
        price = 19.99,
        discountPercentage = 5.5,
        thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Eyeshadow%20Palette%20with%20Mirror/thumbnail.png",
        quantity = 1
    )
)

internal val sampleCartSummary: CartSummary = CartSummary(
    subtotal = 39.97,
    discountTotal = 2.53,
    finalTotal = 37.44,
    totalItemCount = 3
)

internal val sampleAuthUser: AuthUser = AuthUser(
    id = 1,
    username = "emilys",
    email = "emily.johnson@x.dummyjson.com",
    firstName = "Emily",
    lastName = "Johnson",
    gender = "female",
    image = "https://dummyjson.com/icon/emilys/128"
)

/**
 * All unique image URLs referenced across the screenshot suite.
 * Preloading these before capturing ensures fast, deterministic rendering.
 */
internal val allSampleImageUrls: List<String> = buildList {
    addAll(sampleProducts.map { it.thumbnail })
    addAll(sampleProducts.flatMap { it.images })
    addAll(samplePerfumeProducts.map { it.thumbnail })
    addAll(samplePerfumeProducts.flatMap { it.images })
    addAll(sampleCartItems.map { it.thumbnail })
    add(sampleAuthUser.image)
}.filter { it.isNotBlank() && it.startsWith("http") }.distinct()
