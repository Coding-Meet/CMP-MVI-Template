package com.example.cmp_mvi_template.core.utility

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
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
 * Aggregated window information used across the app.
 *
 * Provides:
 * - Exact width and height categories
 * - Convenient boolean flags for simpler UI conditions
 *
 * Marked as @Stable to help Compose avoid unnecessary recompositions.
 */
data class WindowInfo(
    val widthType: WindowWidthType,
    val heightType: WindowHeightType,

    val isCompact: Boolean,
    val isMedium: Boolean,
    val isExpanded: Boolean,
    val isLarge: Boolean,
    val isExtraLarge: Boolean,

    val isHeightCompact: Boolean,
    val isHeightMedium: Boolean,
    val isHeightExpanded: Boolean
) {

    /**
     * Returns true for tablet-like devices.
     *
     * Includes:
     * - MEDIUM (portrait tablets, foldables)
     * - EXPANDED (landscape tablets)
     *
     * Use when you want a "tablet UI" regardless of orientation.
     */
    val isTablet: Boolean
        get() = widthType == WindowWidthType.MEDIUM ||
                widthType == WindowWidthType.EXPANDED

    /**
     * Returns true for desktop-class screens.
     *
     * Includes:
     * - LARGE
     * - EXTRA_LARGE
     *
     * Use for:
     * - dashboards
     * - multi-pane layouts
     * - productivity UI
     */
    val isDesktop: Boolean
        get() = widthType == WindowWidthType.LARGE ||
                widthType == WindowWidthType.EXTRA_LARGE

    /**
     * Returns true for any device that is NOT compact.
     *
     * Includes:
     * - tablets
     * - desktops
     *
     * Useful for enabling:
     * - two-pane layouts
     * - side-by-side content
     */
    val isAtLeastTablet: Boolean
        get() = widthType != WindowWidthType.COMPACT

    /**
     * Returns true for screens that are expanded or larger.
     *
     * Includes:
     * - EXPANDED
     * - LARGE
     * - EXTRA_LARGE
     *
     * Useful for:
     * - showing sidebars
     * - persistent navigation
     * - advanced layouts
     */
    val isAtLeastExpanded: Boolean
        get() = widthType == WindowWidthType.EXPANDED ||
                widthType == WindowWidthType.LARGE ||
                widthType == WindowWidthType.EXTRA_LARGE
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

    // Get current adaptive window info from Compose
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val sizeClass = windowAdaptiveInfo.windowSizeClass

    // Determine width category (largest to smallest)
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

    // Determine height category
    val heightType = when {
        sizeClass.isHeightAtLeastBreakpoint(
            WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND // 900dp
        ) -> WindowHeightType.EXPANDED

        sizeClass.isHeightAtLeastBreakpoint(
            WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND // 480dp
        ) -> WindowHeightType.MEDIUM

        else -> WindowHeightType.COMPACT
    }

    // Return aggregated result with helper flags
    return WindowInfo(
        widthType = widthType,
        heightType = heightType,

        isCompact = widthType == WindowWidthType.COMPACT,
        isMedium = widthType == WindowWidthType.MEDIUM,
        isExpanded = widthType == WindowWidthType.EXPANDED,
        isLarge = widthType == WindowWidthType.LARGE,
        isExtraLarge = widthType == WindowWidthType.EXTRA_LARGE,

        isHeightCompact = heightType == WindowHeightType.COMPACT,
        isHeightMedium = heightType == WindowHeightType.MEDIUM,
        isHeightExpanded = heightType == WindowHeightType.EXPANDED
    )
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
        // Medium + Expanded → tablets / foldables
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
        // Tablet + Desktop → enough space for 2 panes
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
        // Expanded + Large + ExtraLarge
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
private fun ExampleGridColumns(): Int {
    val window = rememberWindowInfo()

    return when {
        window.isCompact -> 2       // phones
        window.isTablet -> 4        // tablets
        window.isExpanded -> 6      // large tablets
        window.isDesktop -> 8       // desktop
        else -> 10                  // very large screens
    }
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