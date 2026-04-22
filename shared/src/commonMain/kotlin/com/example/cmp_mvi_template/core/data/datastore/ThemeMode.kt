package com.example.cmp_mvi_template.core.data.datastore

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Smartphone
import cmp_mvi_template.composeapp.generated.resources.*
import com.example.cmp_mvi_template.core.utility.IconResource
import com.example.cmp_mvi_template.core.utility.UiText

enum class ThemeMode(
    val icon: IconResource,
    val screenTitle: UiText,
    val title: UiText,
    val description: UiText
) {
    LIGHT(
        icon = IconResource.ImageVector(Icons.Outlined.LightMode),
        screenTitle = UiText.StringResource(Res.string.theme_light_screen_title),
        title = UiText.StringResource(Res.string.theme_light_title),
        description = UiText.StringResource(Res.string.theme_light_description)
    ),
    DARK(
        icon = IconResource.ImageVector(Icons.Outlined.DarkMode),
        screenTitle = UiText.StringResource(Res.string.theme_dark_screen_title),
        title = UiText.StringResource(Res.string.theme_dark_title),
        description = UiText.StringResource(Res.string.theme_dark_description)
    ),
    SYSTEM(
        icon = IconResource.ImageVector(Icons.Outlined.Smartphone),
        screenTitle = UiText.StringResource(Res.string.theme_system_screen_title),
        title = UiText.StringResource(Res.string.theme_system_title),
        description = UiText.StringResource(Res.string.theme_system_description)
    )
}
