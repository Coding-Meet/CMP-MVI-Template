package com.example.cmp_mvi_template.feature.onboarding.presentation.screen

sealed interface OnboardingEvent {
    data object NextPage : OnboardingEvent
    data object PreviousPage : OnboardingEvent
    data object CompleteOnboarding : OnboardingEvent
    data object SkipOnboarding : OnboardingEvent
    data class PageChanged(val page: Int) : OnboardingEvent
}
