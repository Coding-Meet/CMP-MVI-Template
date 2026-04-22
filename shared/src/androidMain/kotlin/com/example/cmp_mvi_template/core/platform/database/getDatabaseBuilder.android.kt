package com.example.cmp_mvi_template.core.platform.database

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cmp_mvi_template.core.data.local.DB_Name
import com.example.cmp_mvi_template.core.data.local.PokemonDatabase
import org.koin.mp.KoinPlatform

actual fun getDatabaseBuilder(): RoomDatabase.Builder<PokemonDatabase> {
    val appContext = KoinPlatform.getKoin().get<Application>()
    val dbFile = appContext.getDatabasePath(DB_Name)
    return Room.databaseBuilder<PokemonDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}