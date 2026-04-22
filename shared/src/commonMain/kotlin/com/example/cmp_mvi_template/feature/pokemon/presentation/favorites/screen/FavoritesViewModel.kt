package com.example.cmp_mvi_template.feature.pokemon.presentation.favorites.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmp_mvi_template.composeapp.generated.resources.*
import com.example.cmp_mvi_template.core.domain.onError
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon
import com.example.cmp_mvi_template.feature.pokemon.domain.usecase.GetFavoritesPokemonUseCase
import com.example.cmp_mvi_template.feature.pokemon.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesPokemonUseCase: GetFavoritesPokemonUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(FavoritesState())
    val state = _state
        .onStart {
            handleEvent(FavoritesEvent.LoadFavorites)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )
    
    private val _effect = MutableSharedFlow<FavoritesEffect>()
    val effect = _effect.asSharedFlow()
    
    fun handleEvent(event: FavoritesEvent) {
        when (event) {
            FavoritesEvent.LoadFavorites -> loadFavorites()
            is FavoritesEvent.RemoveFromFavorites -> removeFromFavorites(event.pokemon)
            is FavoritesEvent.NavigateToPokemonDetails -> navigateToDetails(event.pokemonId)
            FavoritesEvent.ClearError -> clearError()
        }
    }
    
    private fun loadFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            getFavoritesPokemonUseCase()
                .catch { error ->
                    _state.update { it ->
                        it.copy(
                            isLoading = false,
                            error =
                                error.message?.let { message->
                                    UiText.DynamicString(message)
                                } ?: UiText.StringResource(Res.string.error_failed_to_load_favorites)
                        )
                    }
                }
                .collect { favoritesPokemon ->
                    _state.update { 
                        it.copy(
                            favoritesPokemon = favoritesPokemon,
                            isLoading = false,
                            error = null
                        ) 
                    }
                }
        }
    }
    
    private fun removeFromFavorites(pokemon: Pokemon) {
        viewModelScope.launch {
            toggleFavoriteUseCase(pokemon)
                .onSuccess {
                    _effect.emit(
                        FavoritesEffect.ShowSuccess(
                            UiText.StringResource(Res.string.success_removed_from_favorites, pokemon.name)
                        )
                    )
                }
                .onError { error ->
                    _effect.emit(FavoritesEffect.ShowError(error.toUiText()))
                }
        }
    }
    
    private fun navigateToDetails(pokemonId: Int) {
        viewModelScope.launch {
            _effect.emit(FavoritesEffect.NavigateToDetails(pokemonId))
        }
    }
    
    private fun clearError() {
        _state.update { it.copy(error = null) }
    }
}