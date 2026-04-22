package com.example.cmp_mvi_template.ui.component.top_app_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import cmp_mvi_template.composeapp.generated.resources.*
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.IconResource
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.ui.component.icon_button.CustomIconButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSmallTopAppBar(
    title: UiText,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigationIcon: IconResource? = null,
    onNavigationClick: (() -> Unit)?,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title.asString(), maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            if (navigationIcon != null && onNavigationClick != null) {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        navigationIcon.asPainterResource(),
                        contentDescription = title.asString()
                    )
                }
            }
        }, colors = colors, scrollBehavior = scrollBehavior, actions = actions, modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMediumTopAppBar(
    title: UiText,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigationIcon: IconResource? = null,
    onNavigationClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    MediumTopAppBar(title = {
        Text(
            text = title.asString(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }, navigationIcon = {
        if (navigationIcon != null) {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    navigationIcon.asPainterResource(),
                    contentDescription = title.asString()
                )
            }
        }
    }, colors = colors, scrollBehavior = scrollBehavior, actions = actions, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomLargeTopAppBar(
    title: UiText,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.largeTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigationIcon: IconResource? = null,
    onNavigationClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    LargeTopAppBar(title = {
        Text(
            text = title.asString(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }, navigationIcon = {
        if (navigationIcon != null) {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    navigationIcon.asPainterResource(),
                    contentDescription = title.asString()
                )
            }
        }
    }, colors = colors, scrollBehavior = scrollBehavior, actions = actions, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCenterAlignedTopAppBar(
    title: UiText,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigationIcon: IconResource? = null,
    onNavigationClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title.asString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        navigationIcon.asPainterResource(),
                        contentDescription = title.asString()
                    )
                }
            }
        },
        colors = colors, scrollBehavior = scrollBehavior, actions = actions, modifier = modifier
    )
}

@DefaultPreview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarPreview() {
    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
            ) {
                CustomSmallTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    navigationIcon = IconResource.ImageVector(Icons.Default.Menu),
                    onNavigationClick = {},
                    actions = {
                        CustomIconButton(
                            icon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                            contentDescription = UiText.StringResource(Res.string.app_name)
                                .asString(),
                            onClick = {}
                        )
                        CustomIconButton(
                            icon = IconResource.ImageVector(Icons.Default.Settings),
                            contentDescription = UiText.StringResource(Res.string.app_name)
                                .asString(),
                            onClick = {}
                        )
                    }
                )
                CustomMediumTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    navigationIcon = IconResource.ImageVector(Icons.AutoMirrored.Filled.ArrowBack),
                    onNavigationClick = {}
                )
                CustomLargeTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    navigationIcon = IconResource.ImageVector(Icons.Default.Home),
                    onNavigationClick = {}
                )
                CustomCenterAlignedTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    navigationIcon = IconResource.ImageVector(Icons.Default.Search),
                    onNavigationClick = {}
                )

                CustomSmallTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    navigationIcon = IconResource.ImageVector(Icons.Default.Menu),
                    onNavigationClick = {}
                )
                CustomMediumTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    navigationIcon = IconResource.ImageVector(Icons.AutoMirrored.Filled.ArrowBack),
                    onNavigationClick = {},
                    actions = {
                        CustomIconButton(
                            icon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                            contentDescription = UiText.StringResource(Res.string.app_name)
                                .asString(),
                            onClick = {}
                        )
                        CustomIconButton(
                            icon = IconResource.ImageVector(Icons.Default.Settings),
                            contentDescription = UiText.StringResource(Res.string.app_name)
                                .asString(),
                            onClick = {}
                        )
                    }
                )
                CustomLargeTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ),
                    navigationIcon = IconResource.ImageVector(Icons.Default.Home),
                    onNavigationClick = {}
                )
                CustomCenterAlignedTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    navigationIcon = IconResource.ImageVector(Icons.Default.Search),
                    onNavigationClick = {}
                )
                CustomCenterAlignedTopAppBar(
                    title = UiText.StringResource(Res.string.app_name),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    navigationIcon = IconResource.ImageVector(Icons.Default.Menu),
                    onNavigationClick = {}
                )
            }
        }
    }
}