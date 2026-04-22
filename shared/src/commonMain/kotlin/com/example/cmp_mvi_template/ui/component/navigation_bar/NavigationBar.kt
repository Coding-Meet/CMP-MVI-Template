package com.example.cmp_mvi_template.ui.component.navigation_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview

data class NavigationItem(
    val title: String,
    val route: String, // you can use here AppDestination or type safe route
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasBadgeDot: Boolean = false,
    val badgeCount: Int? = null
)

@Composable
fun CustomNavigationBar(
    items: List<NavigationItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        tonalElevation = 3.dp
    ) {
        items.forEach { item ->
            CustomNavigationBarItem(
                item = item,
                isSelected = item.route == selectedRoute,
                onClick = { onItemSelected(item.route) }
            )
        }
    }
}

@Composable
fun RowScope.CustomNavigationBarItem(
    item: NavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        icon = {
            if (item.hasBadgeDot || item.badgeCount != null) {
                BadgedBox(
                    badge = {
                        Badge {
                            if (item.badgeCount != null) {
                                Text(text = item.badgeCount.toString())
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unSelectedIcon,
                        contentDescription = item.title
                    )
                }
            } else {
                Icon(
                    imageVector = if (isSelected) item.selectedIcon else item.unSelectedIcon,
                    contentDescription = item.title
                )
            }
        },
        label = { Text(item.title) },
        selected = isSelected,
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            indicatorColor = MaterialTheme.colorScheme.secondaryContainer
        )
    )
}

@Composable
fun SimpleCustomNavigationBar(
    items: List<NavigationItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        items.forEachIndexed { _, item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onItemSelected(item.route) }
            ) {
                Icon(
                    imageVector = item.selectedIcon,
                    contentDescription = item.title,
                    tint = if (selectedRoute == item.route) Color.Blue else Color.Gray
                )
                Text(
                    text = item.title,
                    color = if (selectedRoute == item.route) Color.Blue else Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun NavigationBarWithCenterFAB(
    items: List<NavigationItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    Box {
        // Background for the navigation bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // List of navigation items excluding the FAB

            items.forEachIndexed { _, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onItemSelected(item.route) }
                ) {
                    Icon(
                        imageVector = item.selectedIcon,
                        contentDescription = item.title,
                        tint = if (selectedRoute == item.route) Color.Blue else Color.Gray
                    )
                    Text(
                        text = item.title,
                        color = if (selectedRoute == item.route) Color.Blue else Color.Gray,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
        // Center FAB
        FloatingActionButton(
            onClick = { /* Handle FAB action */ },
            containerColor = Color.Magenta,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-28).dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun AnimatedCustomNavigationBar(
    items: List<NavigationItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { _, item ->
            val color by animateColorAsState(
                if (selectedRoute == item.route) Color.Blue else Color.Gray,
                animationSpec = tween(durationMillis = 300), label = ""
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onItemSelected(item.route) }
            ) {
                Icon(
                    imageVector = item.selectedIcon,
                    contentDescription = item.title,
                    tint = color,
                    modifier = Modifier.size(if (selectedRoute == item.route) 30.dp else 24.dp)
                )
                Text(
                    text = item.title,
                    color = color,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun CustomShapeNavigationBar(
    items: List<NavigationItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { _, item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onItemSelected(item.route) }
            ) {
                Icon(
                    imageVector = item.selectedIcon,
                    contentDescription = item.title,
                    tint = if (selectedRoute == item.route) Color.Magenta else Color.Gray
                )
                Text(
                    text = item.title,
                    color = if (selectedRoute == item.route) Color.Magenta else Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        hasBadgeDot = true
    ),
    NavigationItem(
        title = "Messages",
        route = "messages",
        selectedIcon = Icons.Filled.Email,
        unSelectedIcon = Icons.Outlined.Email,
        badgeCount = 5
    ),
    NavigationItem(
        title = "Profile",
        route = "profile",
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )
)


@DefaultPreview
@Composable
fun NavigationBarPreview() {
    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Bottom
            ) {
                var selectedRoute by remember { mutableStateOf("home") }

                CustomNavigationBar(
                    items = navigationItems,
                    selectedRoute = selectedRoute,
                    onItemSelected = { selectedRoute = it }
                )
                SimpleCustomNavigationBar(
                    items = navigationItems,
                    selectedRoute = selectedRoute,
                    onItemSelected = { selectedRoute = it }
                )
                NavigationBarWithCenterFAB(
                    items = navigationItems,
                    selectedRoute = selectedRoute,
                    onItemSelected = { selectedRoute = it }
                )
                AnimatedCustomNavigationBar(
                    items = navigationItems,
                    selectedRoute = selectedRoute,
                    onItemSelected = { selectedRoute = it }
                )
                AnimatedCustomNavigationBar(
                    items = navigationItems,
                    selectedRoute = selectedRoute,
                    onItemSelected = { selectedRoute = it }
                )
                CustomShapeNavigationBar(
                    items = navigationItems,
                    selectedRoute = selectedRoute,
                    onItemSelected = { selectedRoute = it }
                )
            }
        }
    }
}
