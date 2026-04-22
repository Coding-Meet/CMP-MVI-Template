package com.example.cmp_mvi_template.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {

    @Serializable
    data object PokemonList : AppDestination

    @Serializable
    data object Favorites : AppDestination

    @Serializable
    data class PokemonDetails(val pokemonId: Int) : AppDestination

    @Serializable
    data object Settings : AppDestination


    @Serializable
    data object SampleExampleNavigationEvent : AppDestination

    @Serializable
    data class DetailNavigationEvent(val name: String) : AppDestination
}
