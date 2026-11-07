package com.example.cmp_mvi_template.screenshot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmp_mvi_template.core.data.datastore.ThemeMode
import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser
import com.example.cmp_mvi_template.feature.auth.presentation.screen.AuthState
import com.example.cmp_mvi_template.feature.auth.presentation.screen.ProfileContent
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartItem
import com.example.cmp_mvi_template.feature.cart.presentation.screen.CartContent
import com.example.cmp_mvi_template.feature.cart.presentation.screen.CartState
import com.example.cmp_mvi_template.feature.onboarding.presentation.screen.OnboardingContent
import com.example.cmp_mvi_template.feature.onboarding.presentation.screen.OnboardingState
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductCategory
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductSortOption
import com.example.cmp_mvi_template.feature.product.presentation.favorites.screen.FavoritesContent
import com.example.cmp_mvi_template.feature.product.presentation.favorites.screen.FavoritesState
import com.example.cmp_mvi_template.feature.product.presentation.product_details.screen.ProductDetailsScreenContent
import com.example.cmp_mvi_template.feature.product.presentation.product_details.screen.ProductDetailsState
import com.example.cmp_mvi_template.feature.product.presentation.product_list.screen.ProductListContent
import com.example.cmp_mvi_template.feature.product.presentation.product_list.screen.ProductListState
import com.example.cmp_mvi_template.feature.setting.presentation.component.ThemeSelectionDialog
import com.example.cmp_mvi_template.feature.setting.presentation.screen.SettingContent
import com.example.cmp_mvi_template.feature.setting.presentation.screen.SettingState

// =============================================================================
// Common Screen Content (Calls *Content functions across Mobile, Desktop & Web Screenshots)
// =============================================================================

@Composable
internal fun ProductsCatalogContent(
    products: List<Product> = sampleProducts,
    categories: List<ProductCategory> = sampleCategories,
    selectedCategory: String = "beauty"
) {
    ProductListContent(
        state = ProductListState(
            productList = products,
            categories = categories,
            selectedCategory = selectedCategory,
            isLoading = false
        ),
        onEvent = {},
        onNavigateToDetails = {}
    )
}

@Composable
internal fun ProductsShimmerContent(
    categories: List<ProductCategory> = sampleCategories,
    selectedCategory: String = "beauty"
) {
    ProductListContent(
        state = ProductListState(
            categories = categories,
            selectedCategory = selectedCategory,
            isLoading = true
        ),
        onEvent = {},
        onNavigateToDetails = {}
    )
}

@Composable
internal fun ProductsSearchContent(
    query: String = "perfume",
    products: List<Product> = sampleSearchProducts
) {
    ProductListContent(
        state = ProductListState(
            isSearchBarVisible = true,
            searchQuery = query,
            productList = products,
            isLoading = false
        ),
        onEvent = {},
        onNavigateToDetails = {}
    )
}

@Composable
internal fun ProductSortSheetContent(
    products: List<Product> = sampleProducts,
    categories: List<ProductCategory> = sampleCategories
) {
    ProductListContent(
        state = ProductListState(
            productList = products,
            categories = categories,
            selectedCategory = "beauty",
            showSortBottomSheet = true,
            selectedSort = ProductSortOption.PRICE_LOW_TO_HIGH
        ),
        onEvent = {},
        onNavigateToDetails = {}
    )
}

@Composable
internal fun ProductDetailsContent(
    product: Product = sampleSelectedProduct,
    isFavorite: Boolean = true
) {
    ProductDetailsScreenContent(
        state = ProductDetailsState(
            product = product,
            isLoading = false,
            isFavorite = isFavorite
        ),
        onEvent = {},
        onNavigateBack = {}
    )
}

@Composable
internal fun CartListContent(
    cartItems: List<CartItem> = sampleCartItems
) {
    CartContent(
        state = CartState(
            cartItems = cartItems,
            isLoading = false
        ),
        onEvent = {},
        onNavigateToDetails = {},
        onNavigateToShop = {}
    )
}

@Composable
internal fun CartEmptyContent() {
    CartContent(
        state = CartState(
            cartItems = emptyList(),
            isLoading = false
        ),
        onEvent = {},
        onNavigateToDetails = {},
        onNavigateToShop = {}
    )
}

@Composable
internal fun CheckoutSuccessDialogContent() {
    CartContent(
        state = CartState(
            cartItems = sampleCartItems,
            showCheckoutSuccessDialog = true
        ),
        onEvent = {},
        onNavigateToDetails = {},
        onNavigateToShop = {}
    )
}

@Composable
internal fun FavoritesListContent(
    products: List<Product> = sampleFavoriteProducts
) {
    FavoritesContent(
        state = FavoritesState(
            favoriteProducts = products,
            isLoading = false
        ),
        onEvent = {},
        onNavigateToDetails = {}
    )
}

@Composable
internal fun FavoritesEmptyContent() {
    FavoritesContent(
        state = FavoritesState(
            favoriteProducts = emptyList(),
            isLoading = false
        ),
        onEvent = {},
        onNavigateToDetails = {}
    )
}

@Composable
internal fun ProfileLoggedInContent(
    user: AuthUser = sampleAuthUser
) {
    ProfileContent(
        state = AuthState(
            isLoggedIn = true,
            user = user
        ),
        onEvent = {}
    )
}

@Composable
internal fun ProfileSignInContent() {
    ProfileContent(
        state = AuthState(
            isLoggedIn = false,
            usernameInput = "emilys",
            passwordInput = "emilyspass"
        ),
        onEvent = {}
    )
}

@Composable
internal fun SettingsContent(
    isDark: Boolean = true
) {
    SettingContent(
        themeState = SettingState(
            themeMode = if (isDark) ThemeMode.DARK else ThemeMode.LIGHT,
            isDynamicColor = false
        ),
        onEvent = {}
    )
}

@Composable
internal fun ThemeDialogContent(
    isDark: Boolean = true
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SettingsContent(isDark = isDark)
        ThemeSelectionDialog(
            currentThemeMode = if (isDark) ThemeMode.DARK else ThemeMode.LIGHT,
            onThemeSelected = {},
            onDismiss = {}
        )
    }
}

@Composable
internal fun OnboardingContent() {
    OnboardingContent(
        state = OnboardingState(),
        onEvent = {}
    )
}
