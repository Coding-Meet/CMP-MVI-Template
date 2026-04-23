package com.example.cmp_mvi_template.feature.splash.di

import com.example.cmp_mvi_template.feature.splash.presentation.screen.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashModule = module {
    viewModelOf(::SplashViewModel)
}