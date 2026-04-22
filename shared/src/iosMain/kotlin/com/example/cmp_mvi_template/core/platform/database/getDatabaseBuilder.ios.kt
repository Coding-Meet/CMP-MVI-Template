package com.example.cmp_mvi_template.core.platform.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cmp_mvi_template.core.data.local.DB_Name
import com.example.cmp_mvi_template.core.data.local.PokemonDatabase
import platform.Foundation.NSHomeDirectory

actual fun getDatabaseBuilder(): RoomDatabase.Builder<PokemonDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_Name"
    return Room.databaseBuilder<PokemonDatabase>(
        name = dbFilePath,
    )
}