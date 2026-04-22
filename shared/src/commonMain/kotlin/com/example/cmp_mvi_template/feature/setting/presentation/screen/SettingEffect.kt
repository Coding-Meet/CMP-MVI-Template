package com.example.cmp_mvi_template.feature.setting.presentation.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface  SettingEffect {

    data class ShowSuccess(val message: UiText) : SettingEffect
    data class ShowError(val message: UiText) : SettingEffect
}