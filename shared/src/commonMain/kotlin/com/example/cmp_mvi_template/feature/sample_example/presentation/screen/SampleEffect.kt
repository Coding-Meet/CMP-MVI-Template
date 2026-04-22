package com.example.cmp_mvi_template.feature.sample_example.presentation.screen

import com.example.cmp_mvi_template.core.utility.UiText


sealed interface SampleEffect {

    data object NavigateBack : SampleEffect

    data class NavigateToDetails(val name:String) : SampleEffect

    data class ShowSnackBar(val message: UiText) : SampleEffect
    data class ShowSnackBarWithAction(val message: UiText) : SampleEffect

    data class ShowSuccess(val message: UiText) : SampleEffect
    data class ShowError(val message: UiText) : SampleEffect
}