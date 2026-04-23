package com.example.cmp_mvi_template.feature.onboarding.di

import com.example.cmp_mvi_template.feature.onboarding.presentation.screen.OnboardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onboardingModule = module {
    viewModelOf(::OnboardingViewModel)
}
