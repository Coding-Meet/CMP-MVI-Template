package com.example.cmp_mvi_template.feature.auth.presentation.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface AuthEffect {
    data class ShowSuccess(val message: UiText) : AuthEffect
    data class ShowError(val message: UiText) : AuthEffect
}

