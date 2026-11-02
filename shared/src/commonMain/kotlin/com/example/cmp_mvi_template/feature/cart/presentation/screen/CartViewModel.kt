package com.example.cmp_mvi_template.feature.cart.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.domain.onError
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.cart.domain.usecase.ClearCartUseCase
import com.example.cmp_mvi_template.feature.cart.domain.usecase.GetCartItemsUseCase
import com.example.cmp_mvi_template.feature.cart.domain.usecase.RemoveFromCartUseCase
import com.example.cmp_mvi_template.feature.cart.domain.usecase.UpdateCartQuantityUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val updateCartQuantityUseCase: UpdateCartQuantityUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state = _state
        .onStart {
            handleEvent(CartEvent.LoadCart)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _effect = MutableSharedFlow<CartEffect>()
    val effect = _effect.asSharedFlow()

    fun handleEvent(event: CartEvent) {
        when (event) {
            CartEvent.LoadCart -> loadCart()
            is CartEvent.IncreaseQuantity -> updateQuantity(event.productId, event.currentQuantity + 1)
            is CartEvent.DecreaseQuantity -> updateQuantity(event.productId, event.currentQuantity - 1)
            is CartEvent.RemoveItem -> removeItem(event.productId)
            CartEvent.ClearCart -> clearCart()
            CartEvent.Checkout -> checkout()
            CartEvent.DismissCheckoutDialog -> _state.update { it.copy(showCheckoutSuccessDialog = false) }
            is CartEvent.NavigateToProductDetails -> {
                viewModelScope.launch {
                    _effect.emit(CartEffect.NavigateToDetails(event.productId))
                }
            }
        }
    }

    private fun loadCart() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            getCartItemsUseCase()
                .catch { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = UiText.DynamicString(error.message ?: "Failed to load cart")
                        )
                    }
                }
                .collect { items ->
                    _state.update {
                        it.copy(
                            cartItems = items,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    private fun updateQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            updateCartQuantityUseCase(productId, quantity)
                .onError { error ->
                    _effect.emit(CartEffect.ShowError(error.toUiText()))
                }
        }
    }

    private fun removeItem(productId: Int) {
        viewModelScope.launch {
            removeFromCartUseCase(productId)
                .onError { error ->
                    _effect.emit(CartEffect.ShowError(error.toUiText()))
                }
        }
    }

    private fun clearCart() {
        viewModelScope.launch {
            clearCartUseCase()
        }
    }

    private fun checkout() {
        viewModelScope.launch {
            clearCartUseCase()
            _state.update { it.copy(showCheckoutSuccessDialog = true) }
        }
    }
}
