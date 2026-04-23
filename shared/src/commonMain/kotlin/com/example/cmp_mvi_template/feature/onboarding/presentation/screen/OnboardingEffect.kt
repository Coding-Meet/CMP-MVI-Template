package com.example.cmp_mvi_template.feature.onboarding.presentation.screen

sealed interface OnboardingEffect {
    data object NavigateToMain : OnboardingEffect
}
