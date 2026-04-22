package com.example.cmp_mvi_template.core.platform.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.jthemedetecor.OsThemeDetector
import java.util.function.Consumer

@Composable
actual fun platformSupportsDynamicColor(): Boolean = false

@Composable
actual fun dynamicDarkColorScheme(): ColorScheme = darkColorScheme()

@Composable
actual fun dynamicLightColorScheme(): ColorScheme = lightColorScheme()

@Composable
actual fun isSystemInDarkThemeMode(): Boolean {
    val detector by remember {
        mutableStateOf(
            OsThemeDetector.getDetector()
        )
    }
    val isSupported by remember(detector) {
        derivedStateOf {
            OsThemeDetector.isSupported()
        }
    }
    val isDark by remember(detector.isDark) {
        derivedStateOf {
            detector.isDark
        }
    }
    val isSystemThemeByFoundationLib = isSystemInDarkTheme()

    var isSystemInDarkTheme by rememberSaveable(isDark,isSystemThemeByFoundationLib) {
        mutableStateOf(
            if (isSupported) {
                detector.isDark
            } else {
                isSystemThemeByFoundationLib
            }
        )
    }

    DisposableEffect(isSystemInDarkTheme) {
        val listener = Consumer<Boolean> {
            if (isSupported) {
                isSystemInDarkTheme = it
            }
        }
        detector.registerListener(listener)
        onDispose {
            detector.removeListener(listener)
        }
    }
    return isSystemInDarkTheme
}