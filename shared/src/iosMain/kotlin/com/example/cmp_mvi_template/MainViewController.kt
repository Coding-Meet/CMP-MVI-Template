package com.example.cmp_mvi_template

import androidx.compose.ui.window.ComposeUIViewController
import com.example.cmp_mvi_template.app.presentation.App
import com.example.cmp_mvi_template.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}