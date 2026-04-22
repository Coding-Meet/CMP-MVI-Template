package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import cmp_mvi_template.composeapp.generated.resources.*
import com.example.cmp_mvi_template.core.domain.onError
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.feature.pokemon.domain.usecase.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.ui.navigation.AppDestination
import kotlinx.coroutines.async

class PokemonDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val checkIfFavoriteUseCase: CheckIfFavoriteUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(PokemonDetailsState())
    val state = _state
        .onStart {
            handleEvent(PokemonDetailsEvent.LoadPokemonDetails)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    
    private val _effect = MutableSharedFlow<PokemonDetailsEffect>()
    val effect = _effect.asSharedFlow()

    fun handleEvent(event: PokemonDetailsEvent) {
        val pokemonId = savedStateHandle.toRoute<AppDestination.PokemonDetails>().pokemonId
        when (event) {
            is PokemonDetailsEvent.LoadPokemonDetails -> loadPokemonDetails(pokemonId)
            PokemonDetailsEvent.ToggleFavorite -> toggleFavorite()
            PokemonDetailsEvent.NavigateBack -> navigateBack()
            PokemonDetailsEvent.ClearError -> clearError()
        }
    }
    
    private fun loadPokemonDetails(pokemonId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            // Load pokemon details and favorite status in parallel
            val pokemonDeferred = async { getPokemonDetailsUseCase(pokemonId) }
            val isFavoriteDeferred = async { checkIfFavoriteUseCase(pokemonId) }
            
            val pokemonResult = pokemonDeferred.await()
            val favoriteResult = isFavoriteDeferred.await()
            
            pokemonResult
                .onSuccess { pokemon ->
                    favoriteResult.onSuccess { isFavorite ->
                        _state.update {
                            it.copy(
                                pokemon = pokemon,
                                isLoading = false,
                                isFavorite = isFavorite,
                                error = null
                            )
                        }
                    }.onError {error->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = error.toUiText()
                            )
                        }
                    }
                }
                .onError { error ->
                    _state.update { 
                        it.copy(
                            isLoading = false,
                            error = error.toUiText()
                        ) 
                    }
                }
        }
    }
    
    private fun toggleFavorite() {
        val pokemon = _state.value.pokemon ?: return
        
        viewModelScope.launch {
            _state.update { it.copy(isTogglingFavorite = true) }
            
            toggleFavoriteUseCase(pokemon)
                .onSuccess {
                    val newFavoriteStatus = !_state.value.isFavorite
                    _state.update { 
                        it.copy(
                            isFavorite = newFavoriteStatus,
                            isTogglingFavorite = false
                        ) 
                    }

                    val message = if (newFavoriteStatus) {
                        UiText.StringResource(Res.string.added_to_favorites, pokemon.name)
                    } else {
                        UiText.StringResource(Res.string.removed_from_favorites, pokemon.name)
                    }
                    _effect.emit(PokemonDetailsEffect.ShowSuccess(message))
                }
                .onError { error ->
                    _state.update { it.copy(isTogglingFavorite = false) }
                    _effect.emit(PokemonDetailsEffect.ShowError(error.toUiText()))
                }
        }
    }
    
    private fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(PokemonDetailsEffect.NavigateBack)
        }
    }
    
    private fun clearError() {
        _state.update { it.copy(error = null) }
    }
}