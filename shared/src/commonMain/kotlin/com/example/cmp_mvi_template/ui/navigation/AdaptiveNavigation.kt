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
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldValue
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cmp_mvi_template.feature.auth.presentation.screen.ProfileScreen
import com.example.cmp_mvi_template.feature.cart.domain.usecase.GetCartCountUseCase
import com.example.cmp_mvi_template.feature.cart.presentation.screen.CartScreen
import com.example.cmp_mvi_template.feature.onboarding.presentation.screen.OnboardingScreen
import com.example.cmp_mvi_template.feature.product.presentation.favorites.screen.FavoritesScreen
import com.example.cmp_mvi_template.feature.product.presentation.product_details.screen.ProductDetailsScreen
import com.example.cmp_mvi_template.feature.product.presentation.product_list.screen.ProductListScreen
import com.example.cmp_mvi_template.feature.sample_example.presentation.screen.SampleExampleScreen
import com.example.cmp_mvi_template.feature.setting.presentation.screen.SettingScreen
import com.example.cmp_mvi_template.feature.splash.presentation.screen.SplashScreen
import com.example.cmp_mvi_template.ui.navigation.bottom_navigation_bar.NavigationItem
import com.example.cmp_mvi_template.ui.navigation.bottom_navigation_bar.navigationBar
import org.koin.compose.koinInject

@Composable
fun AdaptiveNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val getCartCountUseCase = koinInject<GetCartCountUseCase>()
    val cartCount by getCartCountUseCase().collectAsStateWithLifecycle(initialValue = 0)

    val currentNavigationItem by remember(navBackStackEntry) {
        derivedStateOf {
            NavigationItem.entries.find { navigationItem ->
                navBackStackEntry.value.isRouteInHierarchy(navigationItem.route::class)
            }
        }
    }

    val currentWindowAdaptiveInfo = currentWindowAdaptiveInfo()
    val layoutType = NavigationSuiteScaffoldDefaults.navigationSuiteType(
        adaptiveInfo = currentWindowAdaptiveInfo
    )
    val navigationSuiteState = rememberNavigationSuiteScaffoldState(
        initialValue = NavigationSuiteScaffoldValue.Hidden
    )
    LaunchedEffect(currentNavigationItem) {
        if (currentNavigationItem != null) {
            navigationSuiteState.show()
        } else {
            navigationSuiteState.hide()
        }
    }
    NavigationSuiteScaffold(
        state = navigationSuiteState,
        navigationSuiteItems = {
            navigationBar(
                currentNavigationItem = currentNavigationItem,
                cartCount = cartCount,
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
            startDestination = AppDestination.Splash,
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

            composable<AppDestination.Splash> {
                SplashScreen(
                    onNavigateToOnboarding = {
                        navController.navigate(AppDestination.Onboarding) {
                            popUpTo(AppDestination.Splash) { inclusive = true }
                        }
                    },
                    onNavigateToMain = {
                        navController.navigate(AppDestination.ProductList) {
                            popUpTo(AppDestination.Splash) { inclusive = true }
                        }
                    },
                )
            }

            composable<AppDestination.Onboarding> {
                OnboardingScreen(
                    onFinished = {
                        navController.navigate(AppDestination.ProductList) {
                            popUpTo(AppDestination.Onboarding) { inclusive = true }
                        }
                    }
                )
            }

            composable<AppDestination.ProductList> {
                ProductListScreen(
                    onNavigateToDetails = { productId ->
                        navController.navigate(AppDestination.ProductDetails(productId))
                    }
                )
            }

            composable<AppDestination.Cart> {
                CartScreen(
                    onNavigateToDetails = { productId ->
                        navController.navigate(AppDestination.ProductDetails(productId))
                    },
                    onNavigateToShop = {
                        navController.navigate(AppDestination.ProductList)
                    }
                )
            }

            composable<AppDestination.Favorites> {
                FavoritesScreen(
                    onNavigateToDetails = { productId ->
                        navController.navigate(AppDestination.ProductDetails(productId))
                    }
                )
            }

            composable<AppDestination.Profile> {
                ProfileScreen()
            }

            composable<AppDestination.Settings> {
                SettingScreen()
            }

            composable<AppDestination.ProductDetails>(
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
                ProductDetailsScreen(
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