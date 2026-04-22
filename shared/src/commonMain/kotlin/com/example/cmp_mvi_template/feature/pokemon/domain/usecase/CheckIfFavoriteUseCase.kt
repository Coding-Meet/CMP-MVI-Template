package com.example.cmp_mvi_template.feature.pokemon.domain.usecase

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.pokemon.domain.repository.PokemonRepository

class CheckIfFavoriteUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: Int): ResultWrapper<Boolean, DataError.Local> {
        return repository.isPokemonFavorite(pokemonId)
    }
}