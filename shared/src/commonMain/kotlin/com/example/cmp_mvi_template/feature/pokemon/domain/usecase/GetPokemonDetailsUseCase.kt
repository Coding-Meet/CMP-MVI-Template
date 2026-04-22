package com.example.cmp_mvi_template.feature.pokemon.domain.usecase

import com.example.cmp_mvi_template.core.domain.*
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.*
import com.example.cmp_mvi_template.feature.pokemon.domain.repository.*
import com.example.cmp_mvi_template.core.domain.ResultWrapper

class GetPokemonDetailsUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): ResultWrapper<Pokemon, DataError> {
        return repository.getPokemonById(id)
    }
}
