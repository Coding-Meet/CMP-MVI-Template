package com.example.cmp_mvi_template.feature.setting.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.data.datastore.ThemeMode
import com.example.cmp_mvi_template.core.data.datastore.ThemePreferences
import com.example.cmp_mvi_template.core.domain.onError
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.UiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(
    private val themePreferences: ThemePreferences,
) : ViewModel() {
    private val _effect = MutableSharedFlow<SettingEffect>()
    val effect = _effect.asSharedFlow()

    val settingState = combine(
        themePreferences.themeMode,
        themePreferences.useDynamicTheme,
    ) { themeMode, isDynamicColor ->
        SettingState(
            themeMode = themeMode,
            isDynamicColor = isDynamicColor,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SettingState()
    )

    fun handleEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.SetThemeMode -> setThemeMode(event.themeMode)
            is SettingEvent.SetDynamicTheme -> setDynamicTheme(event.useDynamicTheme)
        }
    }

    private fun setThemeMode(themeMode: ThemeMode) {
        viewModelScope.launch {
            themePreferences.setThemeMode(
                themeMode
            ).onSuccess {
                _effect.emit(SettingEffect.ShowSuccess(UiText.DynamicString("Theme Mode Changed")))
            }.onError {
                _effect.emit(SettingEffect.ShowError(it.toUiText()))
            }
        }
    }

    private fun setDynamicTheme(useDynamicTheme: Boolean) {
        viewModelScope.launch {
            themePreferences.setDynamicTheme(useDynamicTheme)
                .onSuccess {
                    _effect.emit(SettingEffect.ShowSuccess(UiText.DynamicString("Dynamic Theme Changed")))
                }.onError {
                    _effect.emit(SettingEffect.ShowError(it.toUiText()))
                }
        }
    }

}
