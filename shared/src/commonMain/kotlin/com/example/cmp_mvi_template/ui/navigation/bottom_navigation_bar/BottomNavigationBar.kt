package com.example.cmp_mvi_template.ui.navigation.bottom_navigation_bar

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextOverflow

fun NavigationSuiteScope.navigationBar(
    currentNavigationItem: NavigationItem?,
    cartCount: Int = 0,
    onItemClick: (NavigationItem) -> Unit
) {
    NavigationItem.entries.forEach { navigationItem ->
        val selected by derivedStateOf {
            currentNavigationItem?.route == navigationItem.route
        }

        item(
            selected = selected,
            alwaysShowLabel = false,
            onClick = { onItemClick(navigationItem) },
            icon = {
                if (navigationItem == NavigationItem.CART && cartCount > 0) {
                    BadgedBox(
                        badge = {
                            Badge {
                                Text(text = if (cartCount > 99) "99+" else "$cartCount")
                            }
                        }
                    ) {
                        Icon(
                            (if (selected) navigationItem.selectedIcon else navigationItem.unSelectedIcon).asPainterResource(),
                            contentDescription = navigationItem.title.asString(),
                        )
                    }
                } else {
                    Icon(
                        (if (selected) navigationItem.selectedIcon else navigationItem.unSelectedIcon).asPainterResource(),
                        contentDescription = navigationItem.title.asString(),
                    )
                }
            },
            label = {
                Text(
                    text = navigationItem.title.asString(),
                    style = if (selected) MaterialTheme.typography.labelLarge
                    else MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
        )
    }
}
