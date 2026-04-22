package com.example.cmp_mvi_template.core.platform.theme

import androidx.compose.runtime.Composable
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.ui.platform.LocalContext
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
actual fun platformSupportsDynamicColor(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
actual fun dynamicDarkColorScheme(): ColorScheme {
    return dynamicDarkColorScheme(LocalContext.current)
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
actual fun dynamicLightColorScheme(): ColorScheme {
    return dynamicLightColorScheme(LocalContext.current)
}

@Composable
actual fun isSystemInDarkThemeMode(): Boolean {
    return isSystemInDarkTheme()
}