package com.example.cmp_mvi_template.feature.product.presentation.product_details.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface ProductDetailsEffect {
    data object NavigateBack : ProductDetailsEffect
    data class ShowError(val message: UiText) : ProductDetailsEffect
    data class ShowSuccess(val message: UiText) : ProductDetailsEffect
}

