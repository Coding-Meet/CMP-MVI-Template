package com.example.cmp_mvi_template.feature.product.presentation.product_list.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.no_products_found
import cmp_mvi_template.composeapp.generated.resources.title_products
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductSortOption
import com.example.cmp_mvi_template.feature.product.presentation.component.ProductListShimmer
import com.example.cmp_mvi_template.feature.product.presentation.product_list.component.CategoryChipsRow
import com.example.cmp_mvi_template.feature.product.presentation.product_list.component.ProductListContent
import com.example.cmp_mvi_template.feature.product.presentation.product_list.component.SortBottomSheet
import com.example.cmp_mvi_template.ui.layout.ErrorMessageLayout
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
            Column {
                if (state.isSearchBarVisible) {
                    TopAppBar(
                        title = {
                            OutlinedTextField(
                                value = state.searchQuery,
                                onValueChange = {
                                    productListViewModel.handleEvent(ProductListEvent.UpdateSearchQuery(it))
                                },
                                placeholder = { Text("Search products...") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(24.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                                ),
                                trailingIcon = {
                                    if (state.searchQuery.isNotEmpty()) {
                                        IconButton(onClick = {
                                            productListViewModel.handleEvent(ProductListEvent.UpdateSearchQuery(""))
                                        }) {
                                            Icon(Icons.Filled.Close, contentDescription = "Clear search")
                                        }
                                    }
                                }
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                productListViewModel.handleEvent(ProductListEvent.ToggleSearchBar(false))
                            }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        }
                    )
                } else {
                    TopAppBar(
                        title = { Text(stringResource(Res.string.title_products)) },
                        actions = {
                            IconButton(onClick = {
                                productListViewModel.handleEvent(ProductListEvent.ToggleSearchBar(true))
                            }) {
                                Icon(Icons.Filled.Search, contentDescription = "Search products")
                            }

                            IconButton(onClick = {
                                productListViewModel.handleEvent(ProductListEvent.ToggleSortBottomSheet(true))
                            }) {
                                BadgedBox(
                                    badge = {
                                        if (state.selectedSort != ProductSortOption.DEFAULT) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(Icons.Filled.Tune, contentDescription = "Sort and filter")
                                }
                            }
                        }
                    )
                }

                // Category Chips (always visible below TopBar)
                if (state.categories.isNotEmpty()) {
                    CategoryChipsRow(
                        categories = state.categories,
                        selectedCategory = state.selectedCategory,
                        onCategorySelected = { categorySlug ->
                            productListViewModel.handleEvent(ProductListEvent.SelectCategory(categorySlug))
                        }
                    )
                }
            }
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

            // Sort BottomSheet
            if (state.showSortBottomSheet) {
                SortBottomSheet(
                    selectedSort = state.selectedSort,
                    onSortSelected = { option ->
                        productListViewModel.handleEvent(ProductListEvent.SelectSortOption(option))
                    },
                    onDismiss = {
                        productListViewModel.handleEvent(ProductListEvent.ToggleSortBottomSheet(false))
                    }
                )
            }
        }
    }
}
