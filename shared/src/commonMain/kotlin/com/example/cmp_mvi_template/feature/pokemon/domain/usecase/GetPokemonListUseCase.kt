package com.example.cmp_mvi_template.feature.pokemon.domain.usecase

import com.example.cmp_mvi_template.core.domain.*
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.*
import com.example.cmp_mvi_template.feature.pokemon.domain.repository.*
import com.example.cmp_mvi_template.core.domain.ResultWrapper
class GetPokemonListUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int = 20, offset: Int = 0): ResultWrapper<PokemonListResponse, DataError> {
        return repository.getPokemonList(limit, offset)
    }
}





