package com.example.cmp_mvi_template.ui.component.bottom_app_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.IconResource
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.ui.component.icon_button.CustomIconButton


@DefaultPreview
@Composable
fun BottomAppBarPreview() {
    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Bottom
            ) {
                BottomAppBar(
                    actions = {
                        CustomIconButton(
                            icon = IconResource.ImageVector(Icons.Default.Home),
                            contentDescription = UiText.DynamicString("Home")
                                .asString(),
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.weight(1f, true))
                        CustomIconButton(
                            icon = IconResource.ImageVector(Icons.Default.Search),
                            contentDescription = UiText.DynamicString("Search")
                                .asString(),
                            onClick = {}
                        )
                        CustomIconButton(
                            icon = IconResource.ImageVector(Icons.Default.Person),
                            contentDescription = UiText.DynamicString("Person")
                                .asString(),
                            onClick = {}
                        )
                    })
                BottomAppBar {
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Home),
                        contentDescription = UiText.DynamicString("Home")
                            .asString(),
                        onClick = {}
                    )
                    Spacer(Modifier.weight(1f, true))
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Settings),
                        contentDescription = UiText.DynamicString("Settings")
                            .asString(),
                        onClick = {}
                    )
                }
                BottomAppBar {
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Home),
                        contentDescription = UiText.DynamicString("Home")
                            .asString(),
                        onClick = {}
                    )
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Search),
                        contentDescription = UiText.DynamicString("Search")
                            .asString(),
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.weight(1f, true))
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Settings),
                        contentDescription = UiText.DynamicString("Settings")
                            .asString(),
                        onClick = {}
                    )
                }

                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ) {
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Home),
                        contentDescription = UiText.DynamicString("Home")
                            .asString(),
                        onClick = {}
                    )
                    Spacer(Modifier.weight(1f, true))
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Settings),
                        contentDescription = UiText.DynamicString("Settings")
                            .asString(),
                        onClick = {}
                    )
                }

                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ) {
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Home),
                        contentDescription = UiText.DynamicString("Home")
                            .asString(),
                        onClick = {}
                    )
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Search),
                        contentDescription = UiText.DynamicString("Search")
                            .asString(),
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.weight(1f, true))
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Settings),
                        contentDescription = UiText.DynamicString("Settings")
                            .asString(),
                        onClick = {}
                    )
                }
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                ) {
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Home),
                        contentDescription = UiText.DynamicString("Home")
                            .asString(),
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.weight(1f, true))
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Search),
                        contentDescription = UiText.DynamicString("Search")
                            .asString(),
                        onClick = {}
                    )
                    CustomIconButton(
                        icon = IconResource.ImageVector(Icons.Default.Person),
                        contentDescription = UiText.DynamicString("Person")
                            .asString(),
                        onClick = {}
                    )
                }
            }
        }
    }
}