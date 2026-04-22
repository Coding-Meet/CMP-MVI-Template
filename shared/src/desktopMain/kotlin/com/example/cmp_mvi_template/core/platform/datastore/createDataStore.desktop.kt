package com.example.cmp_mvi_template.core.platform.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun createDataStore(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        dataStoreFileName
    }
}