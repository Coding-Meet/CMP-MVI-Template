package com.example.cmp_mvi_template.core.platform.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
expect fun platformSupportsDynamicColor(): Boolean

@Composable
expect fun dynamicLightColorScheme(): ColorScheme

@Composable
expect fun dynamicDarkColorScheme(): ColorScheme

@Composable
expect fun isSystemInDarkThemeMode(): Boolean

