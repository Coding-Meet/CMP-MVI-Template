package com.example.cmp_mvi_template.feature.onboarding.presentation.screen

import com.example.cmp_mvi_template.feature.onboarding.presentation.model.OnboardingPage
import com.example.cmp_mvi_template.feature.onboarding.presentation.model.onboardingPages

data class OnboardingState(
    val pages: List<OnboardingPage> = onboardingPages(),
    val currentPage: Int = 0,
) {
    val isLastPage: Boolean get() = currentPage == pages.lastIndex
}
