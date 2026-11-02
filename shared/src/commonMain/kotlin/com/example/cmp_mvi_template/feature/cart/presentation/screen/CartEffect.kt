package com.example.cmp_mvi_template.feature.cart.presentation.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface CartEffect {
    data class ShowSuccess(val message: UiText) : CartEffect
    data class ShowError(val message: UiText) : CartEffect
    data class NavigateToDetails(val productId: Int) : CartEffect
}

