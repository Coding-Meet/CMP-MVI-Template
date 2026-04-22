package com.example.cmp_mvi_template.feature.pokemon.di

import com.example.cmp_mvi_template.feature.pokemon.data.remote.api.*
import com.example.cmp_mvi_template.feature.pokemon.data.remote.api.PokemonApiServiceImpl
import com.example.cmp_mvi_template.feature.pokemon.data.repository.PokemonRepositoryImpl
import com.example.cmp_mvi_template.feature.pokemon.domain.repository.PokemonRepository
import com.example.cmp_mvi_template.feature.pokemon.domain.usecase.*
import com.example.cmp_mvi_template.feature.pokemon.presentation.favorites.screen.FavoritesViewModel
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.screen.PokemonDetailsViewModel
import com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen.PokemonListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val pokemonModule = module {
    // API
    singleOf(::PokemonApiServiceImpl) { bind<PokemonApiService>() }

    // Repository
    singleOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }

    // Use Cases
    singleOf(::GetPokemonListUseCase)
    singleOf(::GetPokemonDetailsUseCase)
    singleOf(::GetFavoritesPokemonUseCase)
    singleOf(::ToggleFavoriteUseCase)
    singleOf(::CheckIfFavoriteUseCase)

    // ViewModels
    viewModelOf(::PokemonListViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::PokemonDetailsViewModel)
}