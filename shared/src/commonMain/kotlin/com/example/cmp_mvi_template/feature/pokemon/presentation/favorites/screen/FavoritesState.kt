package com.example.cmp_mvi_template.feature.pokemon.presentation.favorites.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon

data class FavoritesState(
    val favoritesPokemon: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null
)