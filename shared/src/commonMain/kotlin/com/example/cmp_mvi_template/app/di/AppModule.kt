package com.example.cmp_mvi_template.app.di

import com.example.cmp_mvi_template.app.presentation.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AppViewModel)
}