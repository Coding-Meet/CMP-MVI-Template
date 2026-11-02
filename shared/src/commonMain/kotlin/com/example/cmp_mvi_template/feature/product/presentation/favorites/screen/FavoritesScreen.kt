package com.example.cmp_mvi_template.feature.product.presentation.favorites.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.title_favorites
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.feature.product.presentation.favorites.component.EmptyFavoritesScreen
import com.example.cmp_mvi_template.feature.product.presentation.favorites.component.FavoritesListContent
import com.example.cmp_mvi_template.ui.layout.ErrorMessageLayout
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.presentation.favorites.component.FavoritesGrid
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoritesScreen(
    onNavigateToDetails: (Int) -> Unit,
) {
    val favoritesViewModel = koinViewModel<FavoritesViewModel>()
    val state by favoritesViewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    favoritesViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            is FavoritesEffect.NavigateToDetails -> {
                onNavigateToDetails(effect.productId)
            }
            is FavoritesEffect.ShowError -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
            is FavoritesEffect.ShowSuccess -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.title_favorites)) },
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.error != null -> {
                    ErrorMessageLayout(
                        error = state.error!!,
                        onRetry = {
                            favoritesViewModel.handleEvent(FavoritesEvent.LoadFavorites)
                        }
                    )
                }

                state.favoriteProducts.isEmpty() -> {
                    EmptyFavoritesScreen()
                }

                state.favoriteProducts.isNotEmpty() -> {
                    FavoritesListContent(
                        state = state,
                        favoritesViewModel = favoritesViewModel
                    )
                }
            }
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun FavoritesScreenPreview() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites") },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            FavoritesGrid(
                products = listOf(
                    Product(id = 1, title = "Essence Mascara", price = 9.99, rating = 4.9, brand = "Essence"),
                    Product(id = 2, title = "Eyeshadow Palette", price = 19.99, rating = 4.5, brand = "Glamour")
                ),
                onProductClick = {},
                onDeleteClick = {}
            )
        }
    }
}

