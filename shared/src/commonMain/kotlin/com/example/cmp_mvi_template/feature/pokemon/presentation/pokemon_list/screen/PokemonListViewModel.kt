package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.domain.Paginator
import com.example.cmp_mvi_template.core.domain.getOrNull
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.AppLogger
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonListItem
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonSprites
import com.example.cmp_mvi_template.feature.pokemon.domain.usecase.GetPokemonDetailsUseCase
import com.example.cmp_mvi_template.feature.pokemon.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state = _state
        .onStart {
            handleEvent(PokemonListEvent.LoadPokemon)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _effect = MutableSharedFlow<PokemonListEffect>()
    val effect = _effect.asSharedFlow()

    private var currentOffset = 0
    private val limit = 5
    private val paginator = Paginator(
        initialKey = 0,
        onLoadUpdated = { isLoadingMore ->
            _state.update { it.copy(isLoadingMore = isLoadingMore) }
        },
        onRequest = { offset ->
            AppLogger.d("PokemonListViewModel"){
                "onRequest offset: $offset"
            }
            getPokemonListUseCase(limit, offset)
        },
        getNextKey = { currentOffset, _ ->
            AppLogger.d("PokemonListViewModel"){
                "getNextKey currentOffset: $currentOffset"
            }
            currentOffset + limit
        },
        onError = { error ->
            _state.update {
                it.copy(
                    error = error.toUiText()
                )
            }
        },
        onSuccess = { response, newOffset ->
            AppLogger.d("PokemonListViewModel"){
                "onSuccess newOffset: $newOffset"
            }
            val pokemonWithDetails = loadPokemonDetails(response.results)
            currentOffset = newOffset
            _state.update {
                it.copy(
                    pokemonList = it.pokemonList + pokemonWithDetails,
                    hasMoreData = response.next != null
                )
            }
        },
        endReached = { _, response -> response.next == null }
    )

    fun handleEvent(event: PokemonListEvent) {
        when (event) {
            PokemonListEvent.LoadPokemon -> loadPokemon()
            PokemonListEvent.LoadMorePokemon -> loadMorePokemon()
            is PokemonListEvent.NavigateToPokemonDetails -> navigateToDetails(event.pokemonId)
            PokemonListEvent.ClearError -> clearError()
        }
    }

    private fun loadPokemon() {
        if (_state.value.isLoading) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            paginator.reset()
            paginator.loadNextItems()

            _state.update { it.copy(isLoading = false) }
        }
    }
    private fun loadMorePokemon() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private suspend fun loadPokemonDetails(pokemonList: List<PokemonListItem>): List<Pokemon> {
        return pokemonList.map { pokemonItem ->
            getPokemonDetailsUseCase(pokemonItem.id).getOrNull() ?: createPlaceholderPokemon(
                pokemonItem
            )
        }
    }

    private fun createPlaceholderPokemon(pokemonItem: PokemonListItem): Pokemon {
        return Pokemon(
            id = pokemonItem.id,
            name = pokemonItem.name,
            height = 0,
            weight = 0,
            baseExperience = 0,
            sprites = PokemonSprites(null, null, null),
            types = emptyList(),
            stats = emptyList(),
            abilities = emptyList()
        )
    }

    private fun navigateToDetails(pokemonId: Int) {
        viewModelScope.launch {
            _effect.emit(PokemonListEffect.NavigateToDetails(pokemonId))
        }
    }

    private fun clearError() {
        _state.update { it.copy(error = null) }
    }

}