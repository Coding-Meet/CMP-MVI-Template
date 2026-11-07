package com.example.cmp_mvi_template.feature.product.presentation.favorites.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.error_failed_to_load_favorites
import cmp_mvi_template.composeapp.generated.resources.success_removed_from_favorites
import com.example.cmp_mvi_template.core.domain.onError
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetFavoriteProductsUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.ToggleFavoriteProductUseCase
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
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val toggleFavoriteProductUseCase: ToggleFavoriteProductUseCase
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
            is FavoritesEvent.RemoveFromFavorites -> removeFromFavorites(event.product)
            is FavoritesEvent.NavigateToProductDetails -> navigateToDetails(event.productId)
            FavoritesEvent.ClearError -> clearError()
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            getFavoriteProductsUseCase()
                .catch { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message?.let { msg ->
                                UiText.DynamicString(msg)
                            } ?: UiText.StringResource(Res.string.error_failed_to_load_favorites)
                        )
                    }
                }
                .collect { favoriteProducts ->
                    _state.update {
                        it.copy(
                            favoriteProducts = favoriteProducts,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    private fun removeFromFavorites(product: Product) {
        viewModelScope.launch {
            toggleFavoriteProductUseCase(product)
                .onSuccess {
                    _effect.emit(
                        FavoritesEffect.ShowSuccess(
                            UiText.StringResource(Res.string.success_removed_from_favorites, product.title)
                        )
                    )
                }
                .onError { error ->
                    _effect.emit(FavoritesEffect.ShowError(error.toUiText()))
                }
        }
    }

    private fun navigateToDetails(productId: Int) {
        viewModelScope.launch {
            _effect.emit(FavoritesEffect.NavigateToDetails(productId))
        }
    }

    private fun clearError() {
        _state.update { it.copy(error = null) }
    }
}

