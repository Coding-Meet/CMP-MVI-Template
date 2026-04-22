package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.pokemon.presentation.component.PokemonCard
import com.example.cmp_mvi_template.feature.pokemon.presentation.component.pokemonListLoadingShimmer
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen.PokemonListEvent
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen.PokemonListState
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen.PokemonListViewModel
import com.example.cmp_mvi_template.ui.theme.minCardSize

@Composable
fun PokemonListContent(
    listState: LazyStaggeredGridState,
    state: PokemonListState,
    pokemonListViewModel: PokemonListViewModel
) {
    LazyVerticalStaggeredGrid(
        state = listState,
        columns = StaggeredGridCells.Adaptive(minCardSize),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(
            items = state.pokemonList,
            key = { pokemon ->
                pokemon.id
            }
        ) { pokemon ->
            PokemonCard(
                pokemon = pokemon,
                onPokemonClick = {
                    pokemonListViewModel.handleEvent(
                        PokemonListEvent.NavigateToPokemonDetails(pokemon.id)
                    )
                }
            )
        }

        if (state.isLoadingMore) {
            pokemonListLoadingShimmer()
        }
    }
}