package com.example.cmp_mvi_template.ui.navigation

import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cmp_mvi_template.feature.pokemon.presentation.favorites.screen.FavoritesScreen
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.screen.PokemonDetailsScreen
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen.PokemonListScreen
import com.example.cmp_mvi_template.feature.sample_example.presentation.screen.SampleExampleScreen
import com.example.cmp_mvi_template.feature.setting.presentation.screen.SettingScreen
import com.example.cmp_mvi_template.ui.navigation.bottom_navigation_bar.NavigationItem
import com.example.cmp_mvi_template.ui.navigation.bottom_navigation_bar.navigationBar

@Composable
fun AdaptiveNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val currentNavigationItem by remember(navBackStackEntry) {
        derivedStateOf {
            NavigationItem.entries.find { navigationItem ->
                navBackStackEntry.value.isRouteInHierarchy(navigationItem.route::class)
            }
        }
    }

    val currentWindowAdaptiveInfo = currentWindowAdaptiveInfo()
    val layoutType by remember(currentNavigationItem,currentWindowAdaptiveInfo) {
        derivedStateOf {
            if (currentNavigationItem != null) {
                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(currentWindowAdaptiveInfo)
            } else {
                NavigationSuiteType.None
            }
        }
    }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            navigationBar(
                currentNavigationItem = currentNavigationItem,
                onItemClick = {
                    navController.navigate(it.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        },
        layoutType = layoutType
    ) {
        NavHost(
            navController = navController,
            startDestination = AppDestination.PokemonList,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = spring()
                ) + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = spring()
                ) + fadeOut()
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = spring()
                ) + fadeIn()
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = spring()
                ) + fadeOut()
            }
        ) {

            composable<AppDestination.PokemonList> {
                PokemonListScreen(
                    onNavigateToDetails = { pokemonId ->
                        navController.navigate(AppDestination.PokemonDetails(pokemonId))
                    }
                )
            }

            composable<AppDestination.Favorites> {
                FavoritesScreen(
                    onNavigateToDetails = { pokemonId ->
                        navController.navigate(AppDestination.PokemonDetails(pokemonId))
                    }
                )
            }
            composable<AppDestination.Settings> {
                SettingScreen()
            }

            composable<AppDestination.PokemonDetails>(
                enterTransition = {
                    scaleIn(
                        initialScale = 0.8f,
                        animationSpec = spring()
                    ) + fadeIn()
                },
                exitTransition = {
                    scaleOut(
                        targetScale = 0.8f,
                        animationSpec = spring()
                    ) + fadeOut()
                }
            ) {
                PokemonDetailsScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable<AppDestination.SampleExampleNavigationEvent> {
                SampleExampleScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    },
                    onNavigateToDetails = {
                        navController.navigate(AppDestination.DetailNavigationEvent(it))
                    }
                )
            }
            composable<AppDestination.DetailNavigationEvent>(
                enterTransition = {
                    scaleIn(
                        initialScale = 0.8f,
                        animationSpec = spring()
                    ) + fadeIn()
                },
                exitTransition = {
                    scaleOut(
                        targetScale = 0.8f,
                        animationSpec = spring()
                    ) + fadeOut()
                }
            ) {
                val detailNavigationEvent = it.toRoute<AppDestination.DetailNavigationEvent>()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Detail Screen ${detailNavigationEvent.name}")
                    Button(onClick = {
                        navController.navigateUp()
                    }) {
                        Text("Back")
                    }
                }
            }
        }
    }
}