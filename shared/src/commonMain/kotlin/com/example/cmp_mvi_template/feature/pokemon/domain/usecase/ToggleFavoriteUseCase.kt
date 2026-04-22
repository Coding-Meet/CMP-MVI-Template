package com.example.cmp_mvi_template.feature.pokemon.domain.usecase

import com.example.cmp_mvi_template.core.domain.*
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.*
import com.example.cmp_mvi_template.feature.pokemon.domain.repository.*

class ToggleFavoriteUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: Pokemon): ResultWrapper<Boolean, DataError.Local> {
        val isFavorite = repository.isPokemonFavorite(pokemon.id)
        return isFavorite.onSuccess {
            if (it) {
                repository.removeFromFavorites(pokemon.id)
            } else {
                repository.addToFavorites(pokemon)
            }
        }.onError {
            ResultWrapper.Error(it)
        }
    }
}