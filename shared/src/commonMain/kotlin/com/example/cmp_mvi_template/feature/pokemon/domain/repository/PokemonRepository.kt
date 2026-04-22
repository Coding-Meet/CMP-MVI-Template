package com.example.cmp_mvi_template.feature.pokemon.domain.repository

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonListResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(
        limit: Int = 20,
        offset: Int = 0
    ): ResultWrapper<PokemonListResponse, DataError>

    suspend fun getPokemonById(id: Int): ResultWrapper<Pokemon, DataError>

    // Favorites
    fun getFavoritesPokemon(): Flow<List<Pokemon>>
    suspend fun addToFavorites(pokemon: Pokemon): EmptyResult<DataError.Local>
    suspend fun removeFromFavorites(pokemonId: Int): EmptyResult<DataError.Local>
    suspend fun isPokemonFavorite(pokemonId: Int): ResultWrapper<Boolean,DataError.Local>
}