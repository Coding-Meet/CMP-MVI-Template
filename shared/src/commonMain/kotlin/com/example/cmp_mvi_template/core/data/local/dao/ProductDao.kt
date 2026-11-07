package com.example.cmp_mvi_template.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM favorite_products ORDER BY title ASC")
    fun getAllFavoriteProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM favorite_products WHERE id = :id")
    suspend fun getProductById(id: Int): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("DELETE FROM favorite_products WHERE id = :id")
    suspend fun deleteProductById(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_products WHERE id = :id)")
    suspend fun isProductFavorite(id: Int): Boolean
}

