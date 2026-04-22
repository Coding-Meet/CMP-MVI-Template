package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.no_pokemon_found
import cmp_mvi_template.composeapp.generated.resources.title_pokemon
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.pokemon.presentation.component.PokemonListShimmer
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.component.PokemonListContent
import com.example.cmp_mvi_template.ui.layout.ErrorMessageLayout
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    onNavigateToDetails: (Int) -> Unit,
) {
    val pokemonListViewModel = koinViewModel<PokemonListViewModel>()
    val state by pokemonListViewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyStaggeredGridState()
    val snackBarHostState = remember { SnackbarHostState() }

    // Handle effects
    pokemonListViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            is PokemonListEffect.NavigateToDetails -> {
                onNavigateToDetails(effect.pokemonId)
            }

            is PokemonListEffect.ShowError -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
        }
    }

    LaunchedEffect(state.pokemonList) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.distinctUntilChanged()
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex == state.pokemonList.lastIndex) {
                    pokemonListViewModel.handleEvent(PokemonListEvent.LoadMorePokemon)
                }
            }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.title_pokemon)) },
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) {
        // Content
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            when {
                state.isLoading -> {
                    PokemonListShimmer()
                }

                state.error != null -> {
                    ErrorMessageLayout(
                        error = state.error!!,
                        onRetry = {
                            pokemonListViewModel.handleEvent(PokemonListEvent.LoadPokemon)
                        }
                    )
                }

                state.pokemonList.isEmpty() -> {
                    ErrorMessageLayout(
                        error = UiText.StringResource(Res.string.no_pokemon_found),
                        onRetry = {
                            pokemonListViewModel.handleEvent(PokemonListEvent.LoadPokemon)
                        }
                    )
                }


                state.pokemonList.isNotEmpty() -> {
                    PokemonListContent(listState, state, pokemonListViewModel)
                }
            }
        }
    }
}