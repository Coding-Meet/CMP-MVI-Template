package com.example.cmp_mvi_template.feature.product.presentation.product_list.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.product.presentation.component.ProductCard
import com.example.cmp_mvi_template.feature.product.presentation.component.productListLoadingShimmer
import com.example.cmp_mvi_template.feature.product.presentation.product_list.screen.ProductListEvent
import com.example.cmp_mvi_template.feature.product.presentation.product_list.screen.ProductListState
import com.example.cmp_mvi_template.feature.product.presentation.product_list.screen.ProductListViewModel
import com.example.cmp_mvi_template.ui.theme.minCardSize

@Composable
fun ProductListContent(
    listState: LazyStaggeredGridState,
    state: ProductListState,
    productListViewModel: ProductListViewModel
) {
    LazyVerticalStaggeredGrid(
        state = listState,
        columns = StaggeredGridCells.Adaptive(minCardSize),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(
            items = state.productList,
            key = { product -> product.id }
        ) { product ->
            ProductCard(
                product = product,
                onProductClick = {
                    productListViewModel.handleEvent(
                        ProductListEvent.NavigateToProductDetails(product.id)
                    )
                }
            )
        }

        if (state.isLoadingMore) {
            productListLoadingShimmer()
        }
    }
}

