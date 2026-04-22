package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon

data class PokemonListState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val isLoadingMore: Boolean = false,
    val hasMoreData: Boolean = true
)