package com.example.cmp_mvi_template.feature.cart.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartItem
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartSummary
import com.example.cmp_mvi_template.feature.cart.presentation.component.CartItemCard
import com.example.cmp_mvi_template.feature.cart.presentation.component.CartSummaryCard
import com.example.cmp_mvi_template.feature.cart.presentation.component.CheckoutSuccessDialog
import com.example.cmp_mvi_template.feature.cart.presentation.component.EmptyCartScreen
import com.example.cmp_mvi_template.ui.layout.ErrorMessageLayout
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews
import com.example.cmp_mvi_template.ui.theme.PhonePreview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    onNavigateToDetails: (Int) -> Unit,
    onNavigateToShop: () -> Unit
) {
    val cartViewModel = koinViewModel<CartViewModel>()
    val state by cartViewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    cartViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            is CartEffect.NavigateToDetails -> onNavigateToDetails(effect.productId)
            is CartEffect.ShowError -> snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            is CartEffect.ShowSuccess -> snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping Cart") },
                actions = {
                    if (state.cartItems.isNotEmpty()) {
                        IconButton(onClick = { cartViewModel.handleEvent(CartEvent.ClearCart) }) {
                            Icon(Icons.Filled.DeleteSweep, contentDescription = "Clear cart")
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        CartBody(
            state = state,
            onIncrease = { id, qty -> cartViewModel.handleEvent(CartEvent.IncreaseQuantity(id, qty)) },
            onDecrease = { id, qty -> cartViewModel.handleEvent(CartEvent.DecreaseQuantity(id, qty)) },
            onRemove = { id -> cartViewModel.handleEvent(CartEvent.RemoveItem(id)) },
            onItemClick = { id -> cartViewModel.handleEvent(CartEvent.NavigateToProductDetails(id)) },
            onCheckout = { cartViewModel.handleEvent(CartEvent.Checkout) },
            onShopNow = onNavigateToShop,
            onDismissDialog = { cartViewModel.handleEvent(CartEvent.DismissCheckoutDialog) },
            onRetry = { cartViewModel.handleEvent(CartEvent.LoadCart) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun CartBody(
    state: CartState,
    onIncrease: (Int, Int) -> Unit,
    onDecrease: (Int, Int) -> Unit,
    onRemove: (Int) -> Unit,
    onItemClick: (Int) -> Unit,
    onCheckout: () -> Unit,
    onShopNow: () -> Unit,
    onDismissDialog: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error != null -> {
                ErrorMessageLayout(
                    error = state.error,
                    onRetry = onRetry
                )
            }

            state.cartItems.isEmpty() -> {
                EmptyCartScreen(onShopNowClick = onShopNow)
            }

            state.cartItems.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        items = state.cartItems,
                        key = { item -> item.id }
                    ) { item ->
                        CartItemCard(
                            item = item,
                            onIncrease = { onIncrease(item.id, item.quantity) },
                            onDecrease = { onDecrease(item.id, item.quantity) },
                            onRemove = { onRemove(item.id) },
                            onClick = { onItemClick(item.id) }
                        )
                    }

                    item {
                        CartSummaryCard(
                            summary = state.summary,
                            onCheckout = onCheckout
                        )
                    }
                }
            }
        }

        if (state.showCheckoutSuccessDialog) {
            CheckoutSuccessDialog(onDismiss = onDismissDialog)
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun CartScreenPreview() {
    CartBody(
        state = CartState(
            cartItems = listOf(
                CartItem(id = 1, title = "Essence Mascara", price = 9.99, discountPercentage = 7.17, quantity = 2),
                CartItem(id = 2, title = "Eyeshadow Palette", price = 19.99, discountPercentage = 0.0, quantity = 1)
            )
        ),
        onIncrease = { _, _ -> },
        onDecrease = { _, _ -> },
        onRemove = {},
        onItemClick = {},
        onCheckout = {},
        onShopNow = {},
        onDismissDialog = {},
        onRetry = {}
    )
}
