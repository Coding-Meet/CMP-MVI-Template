package com.example.cmp_mvi_template.feature.pokemon.domain.usecase


import com.example.cmp_mvi_template.feature.pokemon.domain.entity.*
import com.example.cmp_mvi_template.feature.pokemon.domain.repository.*
import kotlinx.coroutines.flow.Flow

class GetFavoritesPokemonUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<List<Pokemon>> {
        return repository.getFavoritesPokemon()
    }
}