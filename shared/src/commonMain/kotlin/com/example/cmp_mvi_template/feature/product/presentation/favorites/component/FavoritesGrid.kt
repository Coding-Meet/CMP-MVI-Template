package com.example.cmp_mvi_template.feature.product.presentation.favorites.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews
import com.example.cmp_mvi_template.ui.theme.minCardSize

@Composable
fun FavoritesGrid(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
    onDeleteClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minCardSize),
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier
    ) {
        items(
            items = products,
            key = { product -> product.id }
        ) { product ->
            SwipeToDeleteProductCard(
                product = product,
                onProductClick = { onProductClick(product.id) },
                onDeleteClick = { onDeleteClick(product) }
            )
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun FavoritesGridPreview() {
    FavoritesGrid(
        products = listOf(
            Product(id = 1, title = "Essence Mascara", price = 9.99, rating = 4.94, brand = "Essence"),
            Product(id = 2, title = "Eyeshadow Palette", price = 19.99, rating = 4.5, brand = "Glamour")
        ),
        onProductClick = {},
        onDeleteClick = {}
    )
}

