package com.example.cmp_mvi_template.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.data.datastore.ThemePreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    themePreferences: ThemePreferences
) : ViewModel() {
    val themeState = combine(
        themePreferences.themeMode,
        themePreferences.useDynamicTheme,
    ) { themeMode, isDynamicColor ->
        AppThemeState(
            themeMode = themeMode,
            isDynamicColor = isDynamicColor,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AppThemeState()
    )
}

