package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface ProductListEffect {
    data class NavigateToDetails(val productId: Int) : ProductListEffect
    data class ShowError(val message: UiText) : ProductListEffect
}

