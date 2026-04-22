package com.example.cmp_mvi_template.app.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cmp_mvi_template.core.data.datastore.ThemeMode
import com.example.cmp_mvi_template.core.platform.theme.isSystemInDarkThemeMode
import com.example.cmp_mvi_template.ui.dialog.ToastPopup
import com.example.cmp_mvi_template.ui.navigation.AdaptiveNavigation
import com.example.cmp_mvi_template.ui.theme.ComposeTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(enableEdgeToEdge: @Composable (Boolean) -> Unit = {}) {
    val appViewModel = koinViewModel<AppViewModel>()
    val themeState by appViewModel.themeState.collectAsStateWithLifecycle()

    val isSystemInDarkThemeMode = isSystemInDarkThemeMode()

    val isDarkMode by  remember(themeState.themeMode, isSystemInDarkThemeMode) {
        derivedStateOf {
            when (themeState.themeMode) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> isSystemInDarkThemeMode
            }
        }
    }
    ComposeTheme(
        themeState.isDynamicColor, isDarkMode
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            enableEdgeToEdge(isDarkMode)
            ToastPopup()
            AdaptiveNavigation()
        }
    }
}