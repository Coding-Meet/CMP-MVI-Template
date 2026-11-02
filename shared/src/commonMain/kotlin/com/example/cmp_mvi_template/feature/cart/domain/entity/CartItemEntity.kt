package com.example.cmp_mvi_template.feature.cart.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val discountPercentage: Double,
    val thumbnail: String,
    val quantity: Int
)
