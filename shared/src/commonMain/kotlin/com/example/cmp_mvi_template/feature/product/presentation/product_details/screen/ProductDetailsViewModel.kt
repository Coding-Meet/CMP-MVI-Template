package com.example.cmp_mvi_template.feature.product.presentation.product_details.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.added_to_favorites
import cmp_mvi_template.composeapp.generated.resources.removed_from_favorites
import com.example.cmp_mvi_template.core.domain.onError
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.cart.data.mapper.toCartItem
import com.example.cmp_mvi_template.feature.cart.domain.usecase.AddToCartUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.CheckIfProductFavoriteUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetProductDetailsUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.ToggleFavoriteProductUseCase
import com.example.cmp_mvi_template.ui.navigation.AppDestination
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val toggleFavoriteProductUseCase: ToggleFavoriteProductUseCase,
    private val checkIfProductFavoriteUseCase: CheckIfProductFavoriteUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailsState())
    val state = _state
        .onStart {
            handleEvent(ProductDetailsEvent.LoadProductDetails(getProductId()))
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _effect = MutableSharedFlow<ProductDetailsEffect>()
    val effect = _effect.asSharedFlow()

    private fun getProductId(): Int {
        return savedStateHandle.toRoute<AppDestination.ProductDetails>().productId
    }

    fun handleEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.LoadProductDetails -> loadProductDetails(event.productId)
            ProductDetailsEvent.ToggleFavorite -> toggleFavorite()
            ProductDetailsEvent.AddToCart -> addToCart()
            ProductDetailsEvent.NavigateBack -> navigateBack()
            ProductDetailsEvent.ClearError -> clearError()
        }
    }

    private fun loadProductDetails(productId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val productDeferred = async { getProductDetailsUseCase(productId) }
            val isFavoriteDeferred = async { checkIfProductFavoriteUseCase(productId) }

            val productResult = productDeferred.await()
            val favoriteResult = isFavoriteDeferred.await()

            productResult
                .onSuccess { product ->
                    favoriteResult.onSuccess { isFavorite ->
                        _state.update {
                            it.copy(
                                product = product,
                                isLoading = false,
                                isFavorite = isFavorite,
                                error = null
                            )
                        }
                    }.onError { error ->
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
        val product = _state.value.product ?: return

        viewModelScope.launch {
            _state.update { it.copy(isTogglingFavorite = true) }

            toggleFavoriteProductUseCase(product)
                .onSuccess {
                    val newFavoriteStatus = !_state.value.isFavorite
                    _state.update {
                        it.copy(
                            isFavorite = newFavoriteStatus,
                            isTogglingFavorite = false
                        )
                    }

                    val message = if (newFavoriteStatus) {
                        UiText.StringResource(Res.string.added_to_favorites, product.title)
                    } else {
                        UiText.StringResource(Res.string.removed_from_favorites, product.title)
                    }
                    _effect.emit(ProductDetailsEffect.ShowSuccess(message))
                }
                .onError { error ->
                    _state.update { it.copy(isTogglingFavorite = false) }
                    _effect.emit(ProductDetailsEffect.ShowError(error.toUiText()))
                }
        }
    }

    private fun addToCart() {
        val product = _state.value.product ?: return
        viewModelScope.launch {
            addToCartUseCase(product.toCartItem(1))
                .onSuccess {
                    _effect.emit(
                        ProductDetailsEffect.ShowSuccess(
                            UiText.DynamicString("${product.title} added to cart!")
                        )
                    )
                }
                .onError { error ->
                    _effect.emit(ProductDetailsEffect.ShowError(error.toUiText()))
                }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(ProductDetailsEffect.NavigateBack)
        }
    }

    private fun clearError() {
        _state.update { it.copy(error = null) }
    }
}
