package com.example.cmp_mvi_template.feature.product.presentation.product_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.product.domain.entity.Product

@Composable
fun ProductDetailsContent(
    product: Product,
    modifier: Modifier = Modifier
) {
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

