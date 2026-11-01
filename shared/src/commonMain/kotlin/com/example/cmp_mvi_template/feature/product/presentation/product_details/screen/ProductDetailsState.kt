package com.example.cmp_mvi_template.feature.product.presentation.product_details.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.product.domain.entity.Product

data class ProductDetailsState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val isTogglingFavorite: Boolean = false,
    val error: UiText? = null
)

