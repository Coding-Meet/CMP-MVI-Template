package com.example.cmp_mvi_template.feature.product.presentation.favorites.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface FavoritesEffect {
    data class NavigateToDetails(val productId: Int) : FavoritesEffect
    data class ShowError(val message: UiText) : FavoritesEffect
    data class ShowSuccess(val message: UiText) : FavoritesEffect
}

