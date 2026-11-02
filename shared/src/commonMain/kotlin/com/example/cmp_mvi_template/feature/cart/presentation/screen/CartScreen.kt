package com.example.cmp_mvi_template.feature.cart.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.cmp_mvi_template.feature.cart.presentation.component.CartItemCard
import com.example.cmp_mvi_template.feature.cart.presentation.component.CartSummaryCard
import com.example.cmp_mvi_template.feature.cart.presentation.component.CheckoutSuccessDialog
import com.example.cmp_mvi_template.feature.cart.presentation.component.EmptyCartScreen
import com.example.cmp_mvi_template.ui.layout.ErrorMessageLayout
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.error != null -> {
                    ErrorMessageLayout(
                        error = state.error!!,
                        onRetry = { cartViewModel.handleEvent(CartEvent.LoadCart) }
                    )
                }

                state.cartItems.isEmpty() -> {
                    EmptyCartScreen(onShopNowClick = onNavigateToShop)
                }

                state.cartItems.isNotEmpty() -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            items = state.cartItems,
                            key = { item -> item.id }
                        ) { item ->
                            CartItemCard(
                                item = item,
                                onIncrease = {
                                    cartViewModel.handleEvent(
                                        CartEvent.IncreaseQuantity(item.id, item.quantity)
                                    )
                                },
                                onDecrease = {
                                    cartViewModel.handleEvent(
                                        CartEvent.DecreaseQuantity(item.id, item.quantity)
                                    )
                                },
                                onRemove = {
                                    cartViewModel.handleEvent(CartEvent.RemoveItem(item.id))
                                },
                                onClick = {
                                    cartViewModel.handleEvent(
                                        CartEvent.NavigateToProductDetails(item.id)
                                    )
                                }
                            )
                        }

                        item {
                            CartSummaryCard(
                                summary = state.summary,
                                onCheckout = {
                                    cartViewModel.handleEvent(CartEvent.Checkout)
                                }
                            )
                        }
                    }
                }
            }

            if (state.showCheckoutSuccessDialog) {
                CheckoutSuccessDialog(
                    onDismiss = {
                        cartViewModel.handleEvent(CartEvent.DismissCheckoutDialog)
                    }
                )
            }
        }
    }
}
