package com.example.cmp_mvi_template.feature.product.di

import com.example.cmp_mvi_template.feature.product.data.remote.api.ProductApiService
import com.example.cmp_mvi_template.feature.product.data.remote.api.ProductApiServiceImpl
import com.example.cmp_mvi_template.feature.product.data.repository.ProductRepositoryImpl
import com.example.cmp_mvi_template.feature.product.domain.repository.ProductRepository
import com.example.cmp_mvi_template.feature.product.domain.usecase.CheckIfProductFavoriteUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetFavoriteProductsUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetProductDetailsUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetProductListUseCase
import com.example.cmp_mvi_template.feature.product.domain.usecase.ToggleFavoriteProductUseCase
import com.example.cmp_mvi_template.feature.product.presentation.favorites.screen.FavoritesViewModel
import com.example.cmp_mvi_template.feature.product.presentation.product_details.screen.ProductDetailsViewModel
import com.example.cmp_mvi_template.feature.product.presentation.product_list.screen.ProductListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val productModule = module {
    // API
    singleOf(::ProductApiServiceImpl) { bind<ProductApiService>() }

    // Repository
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }

    // Use Cases
    singleOf(::GetProductListUseCase)
    singleOf(::GetProductDetailsUseCase)
    singleOf(::GetFavoriteProductsUseCase)
    singleOf(::ToggleFavoriteProductUseCase)
    singleOf(::CheckIfProductFavoriteUseCase)

    // ViewModels
    viewModelOf(::ProductListViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::ProductDetailsViewModel)
}

