package com.example.cmp_mvi_template.feature.setting.presentation.screen

import com.example.cmp_mvi_template.core.data.datastore.ThemeMode

sealed interface SettingEvent {

    data class SetThemeMode(val themeMode: ThemeMode) : SettingEvent
    data class SetDynamicTheme(val useDynamicTheme: Boolean) : SettingEvent
}