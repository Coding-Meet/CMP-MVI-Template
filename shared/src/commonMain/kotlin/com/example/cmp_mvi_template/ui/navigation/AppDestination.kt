package com.example.cmp_mvi_template.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {

    @Serializable
    data object Splash : AppDestination

    @Serializable
    data object Onboarding : AppDestination

    @Serializable
    data object ProductList : AppDestination

    @Serializable
    data object Favorites : AppDestination

    @Serializable
    data class ProductDetails(val productId: Int) : AppDestination

    @Serializable
    data object Settings : AppDestination


    @Serializable
    data object SampleExampleNavigationEvent : AppDestination

    @Serializable
    data class DetailNavigationEvent(val name: String) : AppDestination
}
