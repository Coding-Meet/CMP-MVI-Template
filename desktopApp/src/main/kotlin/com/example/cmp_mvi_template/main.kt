package com.example.cmp_mvi_template

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.example.cmp_mvi_template.app.presentation.App
import com.example.cmp_mvi_template.core.platform.toast.setComposeWindowProvider
import com.example.cmp_mvi_template.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP-MVI-Template",
        state = WindowState(

            // Mobile – Portrait (Compact width)
            // width = 360.dp,
            // height = 800.dp,

            // Mobile – Landscape (Compact height)
            // width = 800.dp,
            // height = 360.dp,

            // Tablet – Portrait (Medium width, Expanded height)
            // width = 700.dp,
            // height = 1000.dp,

            // Tablet – Landscape (Medium width, Medium height)
            // width = 1000.dp,
            // height = 700.dp,

            // Desktop – Small (Expanded width)
            // width = 1200.dp,
            // height = 800.dp,

            // Desktop – Large
            // width = 1600.dp,
            // height = 900.dp,

            // Foldable / Free resize test
            // width = 840.dp,
            // height = 900.dp,
        ),
    ) {
        setComposeWindowProvider {
            window
        }
        App()
    }
}