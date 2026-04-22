package com.example.cmp_mvi_template.feature.pokemon.presentation.favorites.screen

import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon

sealed interface FavoritesEvent {
    data object LoadFavorites : FavoritesEvent
    data class RemoveFromFavorites(val pokemon: Pokemon) : FavoritesEvent
    data class NavigateToPokemonDetails(val pokemonId: Int) : FavoritesEvent
    data object ClearError : FavoritesEvent
}