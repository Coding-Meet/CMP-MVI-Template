package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface PokemonListEffect {
    data class NavigateToDetails(val pokemonId: Int) : PokemonListEffect
    data class ShowError(val message: UiText) : PokemonListEffect
}