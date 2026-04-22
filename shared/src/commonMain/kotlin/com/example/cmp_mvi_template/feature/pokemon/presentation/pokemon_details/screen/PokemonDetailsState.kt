package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon

data class PokemonDetailsState(
    val pokemon: Pokemon? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val isFavorite: Boolean = false,
    val isTogglingFavorite: Boolean = false
)