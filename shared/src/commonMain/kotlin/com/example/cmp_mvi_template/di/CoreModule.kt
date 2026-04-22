package com.example.cmp_mvi_template.di

import com.example.cmp_mvi_template.core.data.datastore.ThemePreferences
import com.example.cmp_mvi_template.core.data.datastore.onboarding.OnboardingPreferences
import com.example.cmp_mvi_template.core.data.local.PokemonDatabase
import com.example.cmp_mvi_template.core.data.network.HttpClientFactory
import com.example.cmp_mvi_template.core.platform.database.getPokemonDatabase
import com.example.cmp_mvi_template.core.platform.datastore.createDataStore
import com.example.cmp_mvi_template.core.platform.toast.toastManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {

    // Toast
    singleOf(::toastManager)

    // Http Client
    single { HttpClientFactory.create() }

    // Database
    singleOf(::getPokemonDatabase)
    single { get<PokemonDatabase>().pokemonDao() }

    // DataStore
    singleOf(::createDataStore)

    // Theme Preference
    singleOf(::ThemePreferences)

    // Onboarding Preference
    singleOf(::OnboardingPreferences)
}