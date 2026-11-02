package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductCategory
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductSortOption

data class ProductListState(
    val productList: List<Product> = emptyList(),
    val categories: List<ProductCategory> = emptyList(),
    val selectedCategory: String? = null,
    val searchQuery: String = "",
    val isSearchBarVisible: Boolean = false,
    val selectedSort: ProductSortOption = ProductSortOption.DEFAULT,
    val showSortBottomSheet: Boolean = false,
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMoreData: Boolean = true,
    val error: UiText? = null
)
