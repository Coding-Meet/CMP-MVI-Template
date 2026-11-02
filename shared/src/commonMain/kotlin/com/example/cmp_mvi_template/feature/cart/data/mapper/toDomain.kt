package com.example.cmp_mvi_template.feature.cart.data.mapper

import com.example.cmp_mvi_template.feature.cart.domain.entity.CartItem
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartItemEntity
import com.example.cmp_mvi_template.feature.product.domain.entity.Product

fun CartItemEntity.toDomain(): CartItem {
    return CartItem(
        id = id,
        title = title,
        price = price,
        discountPercentage = discountPercentage,
        thumbnail = thumbnail,
        quantity = quantity
    )
}

fun CartItem.toEntity(): CartItemEntity {
    return CartItemEntity(
        id = id,
        title = title,
        price = price,
        discountPercentage = discountPercentage,
        thumbnail = thumbnail,
        quantity = quantity
    )
}

fun Product.toCartItem(quantity: Int = 1): CartItem {
    return CartItem(
        id = id,
        title = title,
        price = price,
        discountPercentage = discountPercentage,
        thumbnail = thumbnail,
        quantity = quantity
    )
}
