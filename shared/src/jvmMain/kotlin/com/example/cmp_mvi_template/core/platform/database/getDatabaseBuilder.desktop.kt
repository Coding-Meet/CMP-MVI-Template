package com.example.cmp_mvi_template.core.platform.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cmp_mvi_template.core.data.local.AppDatabase
import com.example.cmp_mvi_template.core.data.local.DB_Name
import java.io.File

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_Name)
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
}
