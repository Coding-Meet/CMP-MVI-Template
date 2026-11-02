package com.example.cmp_mvi_template.feature.setting.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.title_settings
import com.example.cmp_mvi_template.core.data.datastore.ThemeMode
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.feature.setting.presentation.component.DynamicThemeToggleAndroidOnly
import com.example.cmp_mvi_template.feature.setting.presentation.component.ThemePreview
import com.example.cmp_mvi_template.feature.setting.presentation.component.ThemeSelectionDialog
import com.example.cmp_mvi_template.feature.setting.presentation.component.ThemeSelectionRow
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingScreen() {
    val settingViewModel = koinViewModel<SettingViewModel>()
    val themeState by settingViewModel.settingState.collectAsStateWithLifecycle()
    var showThemeDialog by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }

    // Handle effects
    settingViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            is SettingEffect.ShowSuccess -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }

            is SettingEffect.ShowError -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.title_settings)) }
            )
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { paddingValues ->
        SettingScreenContent(
            themeState = themeState,
            onThemeClick = { showThemeDialog = true },
            onDynamicThemeToggle = {
                settingViewModel.handleEvent(SettingEvent.SetDynamicTheme(it))
            },
            modifier = Modifier.padding(paddingValues)
        )
    }

    // Show Theme Selection Dialog
    if (showThemeDialog) {
        ThemeSelectionDialog(
            currentThemeMode = themeState.themeMode,
            onThemeSelected = { themeMode ->
                settingViewModel.handleEvent(SettingEvent.SetThemeMode(themeMode))
            },
            onDismiss = { showThemeDialog = false }
        )
    }
}

@Composable
fun SettingScreenContent(
    themeState: SettingState,
    onThemeClick: () -> Unit,
    onDynamicThemeToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ThemeSelectionRow(
            themeState.themeMode.icon,
            themeState.themeMode.screenTitle,
            onClick = onThemeClick
        )

        // Dynamic Theme Toggle (Android only)
        DynamicThemeToggleAndroidOnly(themeState) {
            onDynamicThemeToggle(it)
        }

        // Theme Preview
        ThemePreview()
    }
}

@AllAdaptivePreviews
@Composable
private fun SettingScreenPreview() {
    SettingScreenContent(
        themeState = SettingState(
            themeMode = ThemeMode.SYSTEM,
            isDynamicColor = true
        ),
        onThemeClick = {},
        onDynamicThemeToggle = {}
    )
}
