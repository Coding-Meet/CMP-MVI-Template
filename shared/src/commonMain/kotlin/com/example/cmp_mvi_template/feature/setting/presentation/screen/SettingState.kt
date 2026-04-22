package com.example.cmp_mvi_template.feature.setting.presentation.screen

import com.example.cmp_mvi_template.core.data.datastore.ThemeMode


data class SettingState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isDynamicColor: Boolean = false,
)