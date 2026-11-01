package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.no_products_found
import cmp_mvi_template.composeapp.generated.resources.title_products
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.product.presentation.component.ProductListShimmer
import com.example.cmp_mvi_template.feature.product.presentation.product_list.component.ProductListContent
import com.example.cmp_mvi_template.ui.layout.ErrorMessageLayout
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductListScreen(
    onNavigateToDetails: (Int) -> Unit,
) {
    val productListViewModel = koinViewModel<ProductListViewModel>()
    val state by productListViewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyStaggeredGridState()
    val snackBarHostState = remember { SnackbarHostState() }

    // Handle effects
    productListViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            is ProductListEffect.NavigateToDetails -> {
                onNavigateToDetails(effect.productId)
            }

            is ProductListEffect.ShowError -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
        }
    }

    LaunchedEffect(state.productList) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.distinctUntilChanged()
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex != null && lastVisibleIndex >= state.productList.lastIndex && state.hasMoreData) {
                    productListViewModel.handleEvent(ProductListEvent.LoadMoreProducts)
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.title_products)) },
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when {
                state.isLoading -> {
                    ProductListShimmer()
                }

                state.error != null -> {
                    ErrorMessageLayout(
                        error = state.error!!,
                        onRetry = {
                            productListViewModel.handleEvent(ProductListEvent.LoadProducts)
                        }
                    )
                }

                state.productList.isEmpty() -> {
                    ErrorMessageLayout(
                        error = UiText.StringResource(Res.string.no_products_found),
                        onRetry = {
                            productListViewModel.handleEvent(ProductListEvent.LoadProducts)
                        }
                    )
                }

                state.productList.isNotEmpty() -> {
                    ProductListContent(listState, state, productListViewModel)
                }
            }
        }
    }
}

