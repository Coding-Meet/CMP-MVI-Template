package com.example.cmp_mvi_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.cmp_mvi_template.app.presentation.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App { darkTheme ->
                val view = LocalView.current
                if (!view.isInEditMode) {
                    SideEffect {
                        val windowInsetsControllerCompat =
                            WindowCompat.getInsetsController(window, view)
                        windowInsetsControllerCompat.isAppearanceLightStatusBars = !darkTheme
                        windowInsetsControllerCompat.isAppearanceLightNavigationBars = !darkTheme
                    }
                }
            }
        }
    }
}