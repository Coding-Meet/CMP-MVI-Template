package com.example.cmp_mvi_template.feature.sample_example.presentation.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface SampleEvent {
    data class NavigateToDetail(val name: String) : SampleEvent

    data object NavigateBack : SampleEvent

    data object LoadingDialogExample : SampleEvent

    data class ShowSystemToast(val message: UiText) : SampleEvent
    data class ShowPopupToast(val message: UiText) : SampleEvent
    data class ShowSnackBar(val message: UiText) : SampleEvent

    data class ShowSnackBarWithAction(val message: UiText) : SampleEvent


}