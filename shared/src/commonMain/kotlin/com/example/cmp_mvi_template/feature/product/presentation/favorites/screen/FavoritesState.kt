package com.example.cmp_mvi_template.feature.product.presentation.favorites.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.product.domain.entity.Product

data class FavoritesState(
    val favoriteProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null
)

