package com.example.cmp_mvi_template.feature.splash.presentation.screen

sealed interface SplashEffect {
    object NavigateToMain : SplashEffect
    object NavigateToOnboarding : SplashEffect
}