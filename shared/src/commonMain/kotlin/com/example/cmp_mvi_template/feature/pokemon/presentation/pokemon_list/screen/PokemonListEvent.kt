package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen

sealed interface PokemonListEvent {
    data object LoadPokemon : PokemonListEvent
    data object LoadMorePokemon : PokemonListEvent
    data class NavigateToPokemonDetails(val pokemonId: Int) : PokemonListEvent
    data object ClearError : PokemonListEvent
}