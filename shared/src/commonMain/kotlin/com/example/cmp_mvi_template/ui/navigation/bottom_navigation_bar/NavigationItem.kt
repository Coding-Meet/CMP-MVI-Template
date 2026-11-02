package com.example.cmp_mvi_template.ui.navigation.bottom_navigation_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.automirrored.outlined.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import cmp_mvi_template.composeapp.generated.resources.*
import com.example.cmp_mvi_template.ui.navigation.AppDestination
import com.example.cmp_mvi_template.core.utility.IconResource
import com.example.cmp_mvi_template.core.utility.UiText

enum class NavigationItem(
    val selectedIcon: IconResource,
    val unSelectedIcon: IconResource,
    val title: UiText,
    val route: AppDestination
) {
    PRODUCTS(
        selectedIcon = IconResource.ImageVector(Icons.AutoMirrored.Filled.List),
        unSelectedIcon = IconResource.ImageVector(Icons.AutoMirrored.Outlined.List),
        title = UiText.StringResource(Res.string.tab_products),
        route = AppDestination.ProductList
    ),
    CART(
        selectedIcon = IconResource.ImageVector(Icons.Filled.ShoppingCart),
        unSelectedIcon = IconResource.ImageVector(Icons.Outlined.ShoppingCart),
        title = UiText.StringResource(Res.string.tab_cart),
        route = AppDestination.Cart
    ),
    FAVORITES(
        selectedIcon = IconResource.ImageVector(Icons.Filled.Favorite),
        unSelectedIcon = IconResource.ImageVector(Icons.Outlined.Favorite),
        title = UiText.StringResource(Res.string.tab_favorites),
        route = AppDestination.Favorites
    ),
    PROFILE(
        selectedIcon = IconResource.ImageVector(Icons.Filled.Person),
        unSelectedIcon = IconResource.ImageVector(Icons.Outlined.Person),
        title = UiText.StringResource(Res.string.tab_profile),
        route = AppDestination.Profile
    ),
    SETTINGS(
        selectedIcon = IconResource.ImageVector(Icons.Filled.Settings),
        unSelectedIcon = IconResource.ImageVector(Icons.Outlined.Settings),
        title = UiText.StringResource(Res.string.tab_settings),
        route = AppDestination.Settings
    )
}
