package com.example.cmp_mvi_template.core.platform.database

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cmp_mvi_template.core.data.local.AppDatabase
import com.example.cmp_mvi_template.core.data.local.DB_Name
import org.koin.mp.KoinPlatform

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = KoinPlatform.getKoin().get<Application>()
    val dbFile = appContext.getDatabasePath(DB_Name)
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
