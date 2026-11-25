package com.example.cmp_mvi_template.feature.product.presentation.product_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.core.utility.rememberWindowInfo
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductReview
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews

@Composable
fun ProductDetailsContent(
    product: Product,
    modifier: Modifier = Modifier
) {
    val windowInfo = rememberWindowInfo()

    if (windowInfo.useHorizontalLayout) {
        // Larger screens (Desktop / Tablet / Landscape): 2-column side-by-side layout
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Left Column: Product Image Gallery (scrollable if needed on smaller window height)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProductImageCarousel(images = product.images.ifEmpty { listOf(product.thumbnail) })
            }

            // Right Column: Product Details (Info, Specs, Reviews)
            LazyColumn(
                modifier = Modifier
                    .weight(1.2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                item {
                    ProductInfoSection(product = product)
                }

                item {
                    ProductSpecsSection(product = product)
                }

                if (product.reviews.isNotEmpty()) {
                    item {
                        ProductReviewsSection(reviews = product.reviews)
                    }
                }
            }
        }
    } else {
        // Compact / Mobile screen: Single-column vertical scroll
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProductImageCarousel(images = product.images.ifEmpty { listOf(product.thumbnail) })
            }

            item {
                ProductInfoSection(product = product)
            }

            item {
                ProductSpecsSection(product = product)
            }

            if (product.reviews.isNotEmpty()) {
                item {
                    ProductReviewsSection(reviews = product.reviews)
                }
            }
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun ProductDetailsContentPreview() {
    ProductDetailsContent(
        product = Product(
            id = 1,
            title = "Essence Mascara Lash Princess",
            description = "The Essence Mascara Lash Princess is a popular mascara known for volumizing lashes.",
            category = "beauty",
            price = 9.99,
            discountPercentage = 7.17,
            rating = 4.94,
            stock = 15,
            brand = "Essence",
            availabilityStatus = "In Stock",
            warrantyInformation = "1 month warranty",
            shippingInformation = "Ships in 2 days",
            returnPolicy = "30 days return",
            reviews = listOf(
                ProductReview(
                    rating = 5,
                    comment = "Super good quality!",
                    date = "2024-05-23T08:56:21.618Z",
                    reviewerName = "Emily"
                )
            )
        )
    )
}
