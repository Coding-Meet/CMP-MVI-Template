package com.example.cmp_mvi_template.feature.auth.di

import com.example.cmp_mvi_template.feature.auth.data.datastore.AuthPreferences
import com.example.cmp_mvi_template.feature.auth.data.remote.api.AuthApiService
import com.example.cmp_mvi_template.feature.auth.data.remote.api.AuthApiServiceImpl
import com.example.cmp_mvi_template.feature.auth.data.repository.AuthRepositoryImpl
import com.example.cmp_mvi_template.feature.auth.domain.repository.AuthRepository
import com.example.cmp_mvi_template.feature.auth.domain.usecase.GetAuthStateUseCase
import com.example.cmp_mvi_template.feature.auth.domain.usecase.LoginUseCase
import com.example.cmp_mvi_template.feature.auth.domain.usecase.LogoutUseCase
import com.example.cmp_mvi_template.feature.auth.presentation.screen.AuthViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    // Preferences
    singleOf(::AuthPreferences)

    // API
    singleOf(::AuthApiServiceImpl) { bind<AuthApiService>() }

    // Repository
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    // Use Cases
    singleOf(::LoginUseCase)
    singleOf(::LogoutUseCase)
    singleOf(::GetAuthStateUseCase)

    // ViewModel
    viewModelOf(::AuthViewModel)
}

