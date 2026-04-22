package com.example.cmp_mvi_template.feature.setting.di

import com.example.cmp_mvi_template.feature.setting.presentation.screen.SettingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingModule = module {
    // Viewmodel
    viewModelOf(::SettingViewModel)
}