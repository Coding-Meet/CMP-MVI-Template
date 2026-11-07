package com.example.cmp_mvi_template.feature.cart.di

import com.example.cmp_mvi_template.feature.cart.data.repository.CartRepositoryImpl
import com.example.cmp_mvi_template.feature.cart.domain.repository.CartRepository
import com.example.cmp_mvi_template.feature.cart.domain.usecase.AddToCartUseCase
import com.example.cmp_mvi_template.feature.cart.domain.usecase.ClearCartUseCase
import com.example.cmp_mvi_template.feature.cart.domain.usecase.GetCartCountUseCase
import com.example.cmp_mvi_template.feature.cart.domain.usecase.GetCartItemsUseCase
import com.example.cmp_mvi_template.feature.cart.domain.usecase.RemoveFromCartUseCase
import com.example.cmp_mvi_template.feature.cart.domain.usecase.UpdateCartQuantityUseCase
import com.example.cmp_mvi_template.feature.cart.presentation.screen.CartViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val cartModule = module {
    // Repository
    singleOf(::CartRepositoryImpl) { bind<CartRepository>() }

    // Use Cases
    singleOf(::GetCartItemsUseCase)
    singleOf(::GetCartCountUseCase)
    singleOf(::AddToCartUseCase)
    singleOf(::UpdateCartQuantityUseCase)
    singleOf(::RemoveFromCartUseCase)
    singleOf(::ClearCartUseCase)

    // ViewModel
    viewModelOf(::CartViewModel)
}

