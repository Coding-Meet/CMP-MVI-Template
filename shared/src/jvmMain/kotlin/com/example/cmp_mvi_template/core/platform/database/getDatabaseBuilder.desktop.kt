package com.example.cmp_mvi_template.core.platform.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cmp_mvi_template.core.data.local.DB_Name
import com.example.cmp_mvi_template.core.data.local.PokemonDatabase
import java.io.File

actual fun getDatabaseBuilder(): RoomDatabase.Builder<PokemonDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_Name)
    return Room.databaseBuilder<PokemonDatabase>(
        name = dbFile.absolutePath,
    )
}