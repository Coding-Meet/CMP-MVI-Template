package com.example.cmp_mvi_template.feature.product.presentation.favorites.screen

import com.example.cmp_mvi_template.feature.product.domain.entity.Product

sealed interface FavoritesEvent {
    data object LoadFavorites : FavoritesEvent
    data class RemoveFromFavorites(val product: Product) : FavoritesEvent
    data class NavigateToProductDetails(val productId: Int) : FavoritesEvent
    data object ClearError : FavoritesEvent
}

