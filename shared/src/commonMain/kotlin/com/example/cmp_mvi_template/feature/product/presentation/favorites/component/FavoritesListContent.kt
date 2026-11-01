package com.example.cmp_mvi_template.feature.product.presentation.favorites.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.product.presentation.favorites.screen.FavoritesEvent
import com.example.cmp_mvi_template.feature.product.presentation.favorites.screen.FavoritesState
import com.example.cmp_mvi_template.feature.product.presentation.favorites.screen.FavoritesViewModel
import com.example.cmp_mvi_template.ui.theme.minCardSize

@Composable
fun FavoritesListContent(
    state: FavoritesState,
    favoritesViewModel: FavoritesViewModel
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minCardSize),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(
            items = state.favoriteProducts,
            key = { product -> product.id }
        ) { product ->
            SwipeToDeleteProductCard(
                product = product,
                onProductClick = {
                    favoritesViewModel.handleEvent(
                        FavoritesEvent.NavigateToProductDetails(product.id)
                    )
                },
                onDeleteClick = {
                    favoritesViewModel.handleEvent(
                        FavoritesEvent.RemoveFromFavorites(product)
                    )
                }
            )
        }
    }
}

