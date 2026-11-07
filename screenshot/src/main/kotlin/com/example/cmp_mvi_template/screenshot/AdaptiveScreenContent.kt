package com.example.cmp_mvi_template.screenshot

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apadmi.cmpdevicepreview.devices.DevicePresets
import com.example.cmp_mvi_template.ui.navigation.bottom_navigation_bar.NavigationItem
import com.example.cmp_mvi_template.ui.navigation.bottom_navigation_bar.navigationBar
import java.io.File

/**
 * All canonical screen states represented in the Store App CMP MVI screenshot suite.
 */
enum class AdaptiveScreenState {
    PRODUCTS_CATALOG,
    PRODUCTS_SHIMMER,
    PRODUCTS_SEARCH,
    PRODUCT_SORT_SHEET,
    PRODUCT_DETAILS,
    CART_LIST,
    CART_EMPTY,
    CHECKOUT_SUCCESS_DIALOG,
    FAVORITES_LIST,
    FAVORITES_EMPTY,
    PROFILE_LOGGED_IN,
    PROFILE_SIGN_IN,
    SETTINGS,
    THEME_DIALOG,
    ONBOARDING,
}

/**
 * Target device platforms supported by the unified adaptive renderer.
 */
enum class TargetPlatform {
    ANDROID,
    IOS,
    MACOS,
    WEB,
}

internal val mobileScreenStates = listOf(
    "01_products_catalog.png" to AdaptiveScreenState.PRODUCTS_CATALOG,
    "02_products_shimmer.png" to AdaptiveScreenState.PRODUCTS_SHIMMER,
    "03_products_search.png" to AdaptiveScreenState.PRODUCTS_SEARCH,
    "04_product_sort_sheet.png" to AdaptiveScreenState.PRODUCT_SORT_SHEET,
    "05_product_details.png" to AdaptiveScreenState.PRODUCT_DETAILS,
    "06_cart_items.png" to AdaptiveScreenState.CART_LIST,
    "07_checkout_success_dialog.png" to AdaptiveScreenState.CHECKOUT_SUCCESS_DIALOG,
    "08_favorites_list.png" to AdaptiveScreenState.FAVORITES_LIST,
    "09_profile_logged_in.png" to AdaptiveScreenState.PROFILE_LOGGED_IN,
    "10_settings_screen.png" to AdaptiveScreenState.SETTINGS,
    "11_theme_selection_dialog.png" to AdaptiveScreenState.THEME_DIALOG,
    "12_onboarding_screen.png" to AdaptiveScreenState.ONBOARDING,
)

internal val desktopScreenStates = listOf(
    "01_desktop_products_catalog.png" to AdaptiveScreenState.PRODUCTS_CATALOG,
    "02_desktop_product_details.png" to AdaptiveScreenState.PRODUCT_DETAILS,
    "03_desktop_cart_summary.png" to AdaptiveScreenState.CART_LIST,
    "04_desktop_favorites_list.png" to AdaptiveScreenState.FAVORITES_LIST,
    "05_desktop_profile_screen.png" to AdaptiveScreenState.PROFILE_LOGGED_IN,
    "06_desktop_settings_screen.png" to AdaptiveScreenState.SETTINGS,
    "07_desktop_theme_dialog.png" to AdaptiveScreenState.THEME_DIALOG,
)

internal val webScreenStates = listOf(
    "01_web_products_catalog.png" to AdaptiveScreenState.PRODUCTS_CATALOG,
    "02_web_product_details.png" to AdaptiveScreenState.PRODUCT_DETAILS,
    "03_web_cart_summary.png" to AdaptiveScreenState.CART_LIST,
    "04_web_favorites_list.png" to AdaptiveScreenState.FAVORITES_LIST,
    "05_web_profile_screen.png" to AdaptiveScreenState.PROFILE_LOGGED_IN,
    "06_web_settings_screen.png" to AdaptiveScreenState.SETTINGS,
    "07_web_theme_dialog.png" to AdaptiveScreenState.THEME_DIALOG,
)

/**
 * Canonical adaptive app container powered by [NavigationSuiteScaffold]:
 * - Compact screens (< 840.dp, e.g. Pixel 9, iPhone 16 Pro):
 *   Uses [NavigationSuiteType.NavigationBar] with active item selection, or
 *   [NavigationSuiteType.None] when viewing product details / onboarding.
 * - Expanded screens (>= 840.dp, e.g. Desktop, Web):
 *   Uses [NavigationSuiteType.NavigationDrawer] permanent navigation panel on the left, or
 *   [NavigationSuiteType.None] for detail / onboarding view.
 */
@Composable
fun AdaptiveAppContent(
    state: AdaptiveScreenState,
    isDark: Boolean = true,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isExpanded = maxWidth >= 840.dp

        val currentNavItem = when (state) {
            AdaptiveScreenState.PRODUCTS_CATALOG,
            AdaptiveScreenState.PRODUCTS_SHIMMER,
            AdaptiveScreenState.PRODUCTS_SEARCH,
            AdaptiveScreenState.PRODUCT_SORT_SHEET -> NavigationItem.PRODUCTS

            AdaptiveScreenState.CART_LIST,
            AdaptiveScreenState.CART_EMPTY,
            AdaptiveScreenState.CHECKOUT_SUCCESS_DIALOG -> NavigationItem.CART

            AdaptiveScreenState.FAVORITES_LIST,
            AdaptiveScreenState.FAVORITES_EMPTY -> NavigationItem.FAVORITES

            AdaptiveScreenState.PROFILE_LOGGED_IN,
            AdaptiveScreenState.PROFILE_SIGN_IN -> NavigationItem.PROFILE

            AdaptiveScreenState.SETTINGS,
            AdaptiveScreenState.THEME_DIALOG -> NavigationItem.SETTINGS

            AdaptiveScreenState.PRODUCT_DETAILS,
            AdaptiveScreenState.ONBOARDING -> null
        }

        val cartCount = when (state) {
            AdaptiveScreenState.CART_EMPTY,
            AdaptiveScreenState.ONBOARDING -> 0
            else -> 2
        }

        val layoutType = if (currentNavItem == null) {
            NavigationSuiteType.None
        } else if (isExpanded) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteType.NavigationBar
        }

        NavigationSuiteScaffold(
            state = rememberNavigationSuiteScaffoldState(),
            layoutType = layoutType,
            navigationSuiteItems = {
                navigationBar(
                    currentNavigationItem = currentNavItem,
                    cartCount = cartCount,
                    onItemClick = {}
                )
            }
        ) {
            when (state) {
                AdaptiveScreenState.PRODUCTS_CATALOG -> ProductsCatalogContent()
                AdaptiveScreenState.PRODUCTS_SHIMMER -> ProductsShimmerContent()
                AdaptiveScreenState.PRODUCTS_SEARCH -> ProductsSearchContent()
                AdaptiveScreenState.PRODUCT_SORT_SHEET -> ProductSortSheetContent()
                AdaptiveScreenState.PRODUCT_DETAILS -> ProductDetailsContent()
                AdaptiveScreenState.CART_LIST -> CartListContent()
                AdaptiveScreenState.CART_EMPTY -> CartEmptyContent()
                AdaptiveScreenState.CHECKOUT_SUCCESS_DIALOG -> CheckoutSuccessDialogContent()
                AdaptiveScreenState.FAVORITES_LIST -> FavoritesListContent()
                AdaptiveScreenState.FAVORITES_EMPTY -> FavoritesEmptyContent()
                AdaptiveScreenState.PROFILE_LOGGED_IN -> ProfileLoggedInContent()
                AdaptiveScreenState.PROFILE_SIGN_IN -> ProfileSignInContent()
                AdaptiveScreenState.SETTINGS -> SettingsContent(isDark = isDark)
                AdaptiveScreenState.THEME_DIALOG -> ThemeDialogContent(isDark = isDark)
                AdaptiveScreenState.ONBOARDING -> OnboardingContent()
            }
        }
    }
}

/**
 * High-level driver that iterates all screen states for [platform] and exports them to [outputDir].
 */
internal fun exportPlatformScreens(
    platform: TargetPlatform,
    outputDir: File,
    isDark: Boolean,
) {
    when (platform) {
        TargetPlatform.ANDROID -> {
            for ((filename, state) in mobileScreenStates) {
                val outFile = File(outputDir, filename)
                renderPhoneScreen(DevicePresets.pixel9, outFile, isDark = isDark) {
                    AdaptiveAppContent(state = state, isDark = isDark)
                }
            }
        }
        TargetPlatform.IOS -> {
            for ((filename, state) in mobileScreenStates) {
                val outFile = File(outputDir, filename)
                renderPhoneScreen(DevicePresets.iPhone16Pro, outFile, isDark = isDark) {
                    AdaptiveAppContent(state = state, isDark = isDark)
                }
            }
        }
        TargetPlatform.MACOS -> {
            for ((filename, state) in desktopScreenStates) {
                val outFile = File(outputDir, filename)
                renderDesktopScreen(DevicePresets.largeDesktopWindow, listOf(outFile), isDark = isDark) {
                    AdaptiveAppContent(state = state, isDark = isDark)
                }
            }
        }
        TargetPlatform.WEB -> {
            for ((filename, state) in webScreenStates) {
                val outFile = File(outputDir, filename)
                renderWebBrowserScreen(outFile, isDark = isDark) {
                    AdaptiveAppContent(state = state, isDark = isDark)
                }
            }
        }
    }
}

