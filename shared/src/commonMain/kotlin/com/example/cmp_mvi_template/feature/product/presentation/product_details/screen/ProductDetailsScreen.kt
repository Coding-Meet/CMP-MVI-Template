package com.example.cmp_mvi_template.feature.product.presentation.product_details.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.add_to_favorites
import cmp_mvi_template.composeapp.generated.resources.back
import cmp_mvi_template.composeapp.generated.resources.remove_from_favorites
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.presentation.product_details.component.ProductDetailsContent
import com.example.cmp_mvi_template.ui.layout.ErrorMessageLayout
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    onNavigateBack: () -> Unit,
) {
    val productDetailsViewModel = koinViewModel<ProductDetailsViewModel>()
    val state by productDetailsViewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    // Handle effects
    productDetailsViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            is ProductDetailsEffect.NavigateBack -> onNavigateBack()
            is ProductDetailsEffect.ShowError -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
            is ProductDetailsEffect.ShowSuccess -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
        }
    }

    ProductDetailsScreenContent(
        state = state,
        snackBarHostState = snackBarHostState,
        onEvent = productDetailsViewModel::handleEvent,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun ProductDetailsScreenContent(
    state: ProductDetailsState,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onEvent: (ProductDetailsEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    ProductDetailsScreenContent(
        state = state,
        snackBarHostState = snackBarHostState,
        onNavigateBack = onNavigateBack,
        onAddToCart = { onEvent(ProductDetailsEvent.AddToCart) },
        onToggleFavorite = { onEvent(ProductDetailsEvent.ToggleFavorite) },
        onRetry = {
            state.product?.id?.let { id ->
                onEvent(ProductDetailsEvent.LoadProductDetails(id))
            }
        }
    )
}

@Composable
fun ProductDetailsScreenContent(
    state: ProductDetailsState,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onNavigateBack: () -> Unit,
    onAddToCart: () -> Unit,
    onToggleFavorite: () -> Unit,
    onRetry: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.product?.title ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.back)
                        )
                    }
                },
                actions = {
                    if (state.product != null) {
                        IconButton(onClick = onToggleFavorite) {
                            AnimatedContent(
                                targetState = state.isFavorite,
                                transitionSpec = {
                                    (scaleIn() + fadeIn()).togetherWith(scaleOut() + fadeOut())
                                },
                                label = "favorite_anim"
                            ) { isFavorite ->
                                Icon(
                                    imageVector = if (isFavorite) {
                                        Icons.Filled.Favorite
                                    } else {
                                        Icons.Outlined.FavoriteBorder
                                    },
                                    contentDescription = if (isFavorite) {
                                        stringResource(Res.string.remove_from_favorites)
                                    } else {
                                        stringResource(Res.string.add_to_favorites)
                                    },
                                    tint = if (isFavorite) {
                                        MaterialTheme.colorScheme.error
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    }
                                )
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (state.product != null) {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    Button(
                        onClick = onAddToCart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Add to Cart • $${state.product.price}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
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
                        error = state.error,
                        onRetry = onRetry
                    )
                }

                state.product != null -> {
                    ProductDetailsContent(
                        product = state.product,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun ProductDetailsScreenPreview() {
    ProductDetailsScreenContent(
        state = ProductDetailsState(
            product = Product(
                id = 1,
                title = "Essence Mascara Lash Princess",
                description = "Volumizing mascara with false lash effect.",
                price = 9.99,
                discountPercentage = 7.17,
                rating = 4.94,
                stock = 5,
                brand = "Essence"
            ),
            isFavorite = true
        ),
        onNavigateBack = {},
        onAddToCart = {},
        onToggleFavorite = {},
        onRetry = {}
    )
}
