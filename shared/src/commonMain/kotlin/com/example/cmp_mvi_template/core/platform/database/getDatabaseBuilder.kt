package com.example.cmp_mvi_template.core.platform.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.cmp_mvi_template.core.data.local.PokemonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

expect fun getDatabaseBuilder(): RoomDatabase.Builder<PokemonDatabase>

fun getPokemonDatabase(): PokemonDatabase {
    return getDatabaseBuilder()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}