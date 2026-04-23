package com.example.cmp_mvi_template.di

import com.example.cmp_mvi_template.app.di.appModule
import com.example.cmp_mvi_template.core.utility.AppLogger
import com.example.cmp_mvi_template.feature.onboarding.di.onboardingModule
import com.example.cmp_mvi_template.feature.pokemon.di.pokemonModule
import com.example.cmp_mvi_template.feature.sample_example.di.sampleExampleModule
import com.example.cmp_mvi_template.feature.setting.di.settingModule
import com.example.cmp_mvi_template.feature.splash.di.splashModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        AppLogger.d(tag = "Koin", message = {"🔥 Initializing Koin"})
        appDeclaration()
        modules(
            coreModule,
            sampleExampleModule,
            appModule,
            onboardingModule,
            splashModule,
            pokemonModule,
            settingModule,
        )
        AppLogger.d(tag = "Koin", message = {"🔥 Koin initialized"})
    }