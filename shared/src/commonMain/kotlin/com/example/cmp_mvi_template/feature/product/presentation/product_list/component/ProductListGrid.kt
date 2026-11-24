package com.example.cmp_mvi_template.feature.product.presentation.product_list.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.presentation.component.ProductCard
import com.example.cmp_mvi_template.feature.product.presentation.component.productListLoadingShimmer
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews
import com.example.cmp_mvi_template.ui.theme.minCardSize

@Composable
fun ProductListGrid(
    listState: LazyStaggeredGridState,
    pagingItems: LazyPagingItems<Product>? = null,
    products: List<Product> = emptyList(),
    isLoadingMore: Boolean = false,
    onProductClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val isAppendLoading by remember(pagingItems, isLoadingMore) {
        derivedStateOf {
            if (pagingItems != null) {
                pagingItems.loadState.append is LoadState.Loading
            } else {
                isLoadingMore
            }
        }
    }

    LazyVerticalStaggeredGrid(
        state = listState,
        columns = StaggeredGridCells.Adaptive(minCardSize),
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier
    ) {
        if (pagingItems != null) {
            items(
                count = pagingItems.itemCount,
                key = pagingItems.itemKey { it.id }
            ) { index ->
                val product = pagingItems[index]
                if (product != null) {
                    ProductCard(
                        product = product,
                        onProductClick = { onProductClick(product.id) }
                    )
                }
            }
        } else {
            items(
                items = products,
                key = { product -> product.id }
            ) { product ->
                ProductCard(
                    product = product,
                    onProductClick = { onProductClick(product.id) }
                )
            }
        }

        if (isAppendLoading) {
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
