package com.example.cmp_mvi_template.feature.product.presentation.product_list.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.presentation.component.ProductCard
import com.example.cmp_mvi_template.feature.product.presentation.component.productListLoadingShimmer
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews
import com.example.cmp_mvi_template.ui.theme.minCardSize

@Composable
fun ProductListGrid(
    listState: LazyStaggeredGridState,
    products: List<Product>,
    isLoadingMore: Boolean,
    onProductClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        state = listState,
        columns = StaggeredGridCells.Adaptive(minCardSize),
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier
    ) {
        items(
            items = products,
            key = { product -> product.id }
        ) { product ->
            ProductCard(
                product = product,
                onProductClick = { onProductClick(product.id) }
            )
        }

        if (isLoadingMore) {
            productListLoadingShimmer()
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun ProductListGridPreview() {
    ProductListGrid(
        listState = rememberLazyStaggeredGridState(),
        products = listOf(
            Product(id = 1, title = "Essence Mascara", price = 9.99, rating = 4.9, brand = "Essence"),
            Product(id = 2, title = "Eyeshadow Palette", price = 19.99, rating = 4.5, brand = "Glamour"),
            Product(id = 3, title = "Powder Canister", price = 14.99, rating = 4.2, brand = "Velvet")
        ),
        isLoadingMore = false,
        onProductClick = {}
    )
}

