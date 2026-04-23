package com.example.cmp_mvi_template.core.utility

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.window.core.layout.WindowSizeClass

/**
 * Represents width categories based on Material adaptive breakpoints.
 *
 * Breakpoints:
 * - COMPACT: width < 600dp (phones)
 * - MEDIUM: 600dp ≤ width < 840dp (tablets portrait / foldables)
 * - EXPANDED: 840dp ≤ width < 1200dp (tablets landscape)
 * - LARGE: 1200dp ≤ width < 1600dp (large tablets)
 * - EXTRA_LARGE: width ≥ 1600dp (desktop)
 */
enum class WindowWidthType {
    COMPACT,
    MEDIUM,
    EXPANDED,
    LARGE,
    EXTRA_LARGE
}

/**
 * Represents height categories based on Material adaptive breakpoints.
 *
 * Breakpoints:
 * - COMPACT: height < 480dp
 * - MEDIUM: 480dp ≤ height < 900dp
 * - EXPANDED: height ≥ 900dp
 */
enum class WindowHeightType {
    COMPACT,
    MEDIUM,
    EXPANDED
}

/**
 * Central UI state for responsive layouts.
 *
 * Keep this minimal and use semantic helpers instead of raw checks.
 */
@Stable
data class WindowInfo(
    val widthType: WindowWidthType,
    val heightType: WindowHeightType
) {

    /** True for phones */
    val isCompact: Boolean
        get() = widthType == WindowWidthType.COMPACT

    /** True for tablets (medium + expanded) */
    val isTablet: Boolean
        get() = widthType == WindowWidthType.MEDIUM ||
                widthType == WindowWidthType.EXPANDED

    /** True for desktop / very large screens */
    val isDesktop: Boolean
        get() = widthType == WindowWidthType.LARGE ||
                widthType == WindowWidthType.EXTRA_LARGE

    /** True for anything bigger than phone */
    val isAtLeastTablet: Boolean
        get() = widthType != WindowWidthType.COMPACT

    /** True for expanded and above (useful for sidebars, etc.) */
    val isAtLeastExpanded: Boolean
        get() = widthType == WindowWidthType.EXPANDED ||
                widthType == WindowWidthType.LARGE ||
                widthType == WindowWidthType.EXTRA_LARGE

    /** True only for expanded (not desktop) */
    val isExpandedOnly: Boolean
        get() = widthType == WindowWidthType.EXPANDED

    /** Height helpers (useful for landscape / compact height UI) */
    /** True for normal height windows  **/
    val isHeightCompact: Boolean
        get() = heightType == WindowHeightType.COMPACT

    /** True for medium height windows */
    val isHeightMedium: Boolean
        get() = heightType == WindowHeightType.MEDIUM

    /** True for tall windows */
    val isHeightExpanded: Boolean
        get() = heightType == WindowHeightType.EXPANDED

    /** True if the UI should prefer a horizontal/side-by-side layout */
    val useHorizontalLayout: Boolean
        get() = isHeightCompact || isAtLeastTablet
}

/**
 * Computes and remembers the current window size classification.
 *
 * This uses the new adaptive API:
 * - isWidthAtLeastBreakpoint
 * - isHeightAtLeastBreakpoint
 *
 * Important:
 * Breakpoints must be checked from largest to smallest because
 * these APIs return true for all larger buckets as well.
 */
@Composable
fun rememberWindowInfo(): WindowInfo {

    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val sizeClass = windowAdaptiveInfo.windowSizeClass

    val widthType = when {
        sizeClass.isWidthAtLeastBreakpoint(1600) -> WindowWidthType.EXTRA_LARGE
        sizeClass.isWidthAtLeastBreakpoint(1200) -> WindowWidthType.LARGE
        sizeClass.isWidthAtLeastBreakpoint(
            WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND // 840dp
        ) -> WindowWidthType.EXPANDED

        sizeClass.isWidthAtLeastBreakpoint(
            WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND // 600dp
        ) -> WindowWidthType.MEDIUM

        else -> WindowWidthType.COMPACT
    }

    val heightType = when {
        sizeClass.isHeightAtLeastBreakpoint(
            WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND // 900dp
        ) -> WindowHeightType.EXPANDED

        sizeClass.isHeightAtLeastBreakpoint(
            WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND // 480dp
        ) -> WindowHeightType.MEDIUM

        else -> WindowHeightType.COMPACT
    }


    return WindowInfo(widthType, heightType)
}
/* ---------------------------------------------------
   Private Usage Examples (with explanations)
   --------------------------------------------------- */

/**
 * Example 1:
 * Basic device split — phone vs tablet/large screens.
 *
 * Use this when UI only has 2 variants:
 * - Compact (phones)
 * - Non-compact (everything else)
 */
@Composable
private fun ExampleTabletCheck() {
    val window = rememberWindowInfo()

    if (window.isTablet) {
        // Tablets + foldables
        TabletLayout()
    } else {
        // Compact → phones
        PhoneLayout()
    }
}

/**
 * Example 2:
 * Check for "at least tablet" size.
 *
 * Useful when enabling multi-pane layouts,
 * like list + detail screens.
 */
@Composable
private fun ExampleAtLeastTablet() {
    val window = rememberWindowInfo()

    if (window.isAtLeastTablet) {
        // Enough space for 2 screens side-by-side
        TwoPaneLayout()
    } else {
        // Phone → single screen UI
        SinglePaneLayout()
    }
}

/**
 * Example 3:
 * Show UI only for large screens.
 *
 * Useful for:
 * - Sidebars
 * - Persistent navigation rails
 * - Extra panels
 */
@Composable
private fun ExampleExpandedOnly() {
    val window = rememberWindowInfo()

    if (window.isAtLeastExpanded) {
        // Show sidebar only on large screens
        ShowSidebar()
    }
}

/**
 * Example 4:
 * Desktop-specific UI.
 *
 * Use when designing:
 * - multi-window layouts
 * - dashboards
 * - advanced productivity UI
 */
@Composable
private fun ExampleDesktopOnly() {
    val window = rememberWindowInfo()

    if (window.isDesktop) {
        DesktopLayout()
    }
}

/**
 * Example 5:
 * Full responsive layout (3 tiers).
 *
 * This is the most common real-world pattern:
 * - Phone
 * - Tablet
 * - Desktop
 */
@Composable
private fun ExampleThreeLayouts() {
    val window = rememberWindowInfo()

    when {
        window.isCompact -> PhoneLayout()
        window.isTablet -> TabletLayout()
        window.isDesktop -> DesktopLayout()
    }
}

/**
 * Example 6:
 * Dynamic grid scaling based on screen size.
 *
 * Useful for:
 * - dashboards
 * - product lists
 * - gallery screens
 */
@Composable
private fun ExampleGridColumns() {
    fun getGridColumns(window: WindowInfo): Int {
        return when {
            window.isCompact -> 2       // phones
            window.isTablet -> 4        // tablets
            window.isExpandedOnly -> 6      // large tablets
            window.isDesktop -> 8       // desktop
            else -> 10                  // very large screens
        }
    }

    val window = rememberWindowInfo()
    val columns = getGridColumns(window)
}

/* Dummy placeholders */

@Composable
private fun PhoneLayout() {
}

@Composable
private fun TabletLayout() {
}

@Composable
private fun DesktopLayout() {
}

@Composable
private fun TwoPaneLayout() {
}

@Composable
private fun SinglePaneLayout() {
}

@Composable
private fun ShowSidebar() {
}