package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.product.domain.entity.Product

data class ProductListState(
    val productList: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMoreData: Boolean = true,
    val error: UiText? = null
)

