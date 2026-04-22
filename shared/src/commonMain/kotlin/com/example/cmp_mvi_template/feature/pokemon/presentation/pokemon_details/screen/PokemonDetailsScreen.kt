package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.add_to_favorites
import cmp_mvi_template.composeapp.generated.resources.back
import cmp_mvi_template.composeapp.generated.resources.remove_from_favorites
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.component.PokemonDetailsContent
import com.example.cmp_mvi_template.ui.layout.ErrorMessageLayout
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(
    onNavigateBack: () -> Unit,
) {
    val pokemonDetailsViewModel = koinViewModel<PokemonDetailsViewModel>()
    val state by pokemonDetailsViewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }


    // Handle effects
    pokemonDetailsViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            PokemonDetailsEffect.NavigateBack -> onNavigateBack()
            is PokemonDetailsEffect.ShowError -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }

            is PokemonDetailsEffect.ShowSuccess -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.pokemon?.name?.replaceFirstChar { it.uppercase() } ?: ""
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        pokemonDetailsViewModel.handleEvent(PokemonDetailsEvent.NavigateBack)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(Res.string.back))
                    }
                },
                actions = {
                    if (state.pokemon != null) {
                        AnimatedContent(
                            targetState = state.isFavorite,
                            transitionSpec = {
                                (scaleIn() + fadeIn()).togetherWith(scaleOut() + fadeOut())
                            },
                            label = "favorite_button"
                        ) { isFavorite ->
                            IconButton(
                                onClick = {
                                    pokemonDetailsViewModel.handleEvent(PokemonDetailsEvent.ToggleFavorite)
                                },
                            ) {
                                if (state.isTogglingFavorite) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(24.dp),
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Icon(
                                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = if (isFavorite) {
                                            stringResource(Res.string.remove_from_favorites)
                                        } else {
                                            stringResource(Res.string.add_to_favorites)
                                        },
                                        tint = if (isFavorite) Color.Red else LocalContentColor.current
                                    )
                                }
                            }
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.pokemon != null -> {
                    PokemonDetailsContent(
                        pokemon = state.pokemon!!,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                state.error != null -> {
                    ErrorMessageLayout(
                        error = state.error!!,
                        onRetry = {
                            pokemonDetailsViewModel.handleEvent(
                                PokemonDetailsEvent.LoadPokemonDetails
                            )
                        }
                    )
                }
            }
        }
    }
}

