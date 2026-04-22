package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.screen

sealed interface PokemonDetailsEvent {
    data object LoadPokemonDetails : PokemonDetailsEvent
    data object ToggleFavorite : PokemonDetailsEvent
    data object NavigateBack : PokemonDetailsEvent
    data object ClearError : PokemonDetailsEvent
}