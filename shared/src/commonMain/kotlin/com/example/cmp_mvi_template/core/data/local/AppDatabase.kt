package com.example.cmp_mvi_template.core.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cmp_mvi_template.core.data.local.dao.ProductDao
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductEntity

const val DB_Name = "app_database.db"
const val DB_Version = 1

@Database(
    entities = [ProductEntity::class],
    version = DB_Version,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

