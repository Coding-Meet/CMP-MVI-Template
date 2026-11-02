package com.example.cmp_mvi_template.feature.cart.data.repository

import androidx.sqlite.SQLiteException
import com.example.cmp_mvi_template.core.data.local.dao.CartDao
import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.cart.data.mapper.toDomain
import com.example.cmp_mvi_template.feature.cart.data.mapper.toEntity
import com.example.cmp_mvi_template.feature.cart.domain.entity.CartItem
import com.example.cmp_mvi_template.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(
    private val cartDao: CartDao
) : CartRepository {

    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDao.getAllCartItems().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getCartTotalCount(): Flow<Int> {
        return cartDao.getCartTotalCount()
    }

    override suspend fun addToCart(cartItem: CartItem): EmptyResult<DataError.Local> {
        return try {
            val existing = cartDao.getCartItemById(cartItem.id)
            if (existing != null) {
                cartDao.updateQuantity(cartItem.id, existing.quantity + cartItem.quantity)
            } else {
                cartDao.insertCartItem(cartItem.toEntity())
            }
            ResultWrapper.Success(Unit)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int): EmptyResult<DataError.Local> {
        return try {
            if (quantity <= 0) {
                cartDao.deleteCartItemById(productId)
            } else {
                cartDao.updateQuantity(productId, quantity)
            }
            ResultWrapper.Success(Unit)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun removeFromCart(productId: Int): EmptyResult<DataError.Local> {
        return try {
            cartDao.deleteCartItemById(productId)
            ResultWrapper.Success(Unit)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun clearCart(): EmptyResult<DataError.Local> {
        return try {
            cartDao.clearCart()
            ResultWrapper.Success(Unit)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }
}
