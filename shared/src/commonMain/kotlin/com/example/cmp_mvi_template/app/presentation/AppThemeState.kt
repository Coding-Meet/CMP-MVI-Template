package com.example.cmp_mvi_template.app.presentation

import com.example.cmp_mvi_template.core.data.datastore.ThemeMode
data class AppThemeState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isDynamicColor: Boolean = false,
)