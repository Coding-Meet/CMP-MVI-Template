package com.example.cmp_mvi_template.core.platform.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun platformSupportsDynamicColor(): Boolean = false

@Composable
actual fun dynamicDarkColorScheme(): ColorScheme = darkColorScheme()

@Composable
actual fun dynamicLightColorScheme(): ColorScheme = lightColorScheme()

@Composable
actual fun isSystemInDarkThemeMode(): Boolean {
    return isSystemInDarkTheme()
}