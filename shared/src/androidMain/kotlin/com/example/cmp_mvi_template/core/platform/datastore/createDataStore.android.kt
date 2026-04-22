package com.example.cmp_mvi_template.core.platform.datastore

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.mp.KoinPlatform

actual fun createDataStore(): DataStore<Preferences> {
    val appContext = KoinPlatform.getKoin().get<Application>()
    return AppSettings.getDataStore(
        producePath = {
            appContext.filesDir
                .resolve(dataStoreFileName)
                .absolutePath
        }
    )
}