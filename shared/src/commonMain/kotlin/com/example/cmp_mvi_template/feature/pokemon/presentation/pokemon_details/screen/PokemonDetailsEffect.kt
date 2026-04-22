package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.screen

import com.example.cmp_mvi_template.core.utility.UiText

sealed interface PokemonDetailsEffect {
    data object NavigateBack : PokemonDetailsEffect
    data class ShowError(val message: UiText) : PokemonDetailsEffect
    data class ShowSuccess(val message: UiText) : PokemonDetailsEffect
}