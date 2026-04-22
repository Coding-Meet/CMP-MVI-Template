package com.example.cmp_mvi_template.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.cmp_mvi_template.core.data.datastore.ThemePreferences.PreferencesKey.THEME_MODE_KEY
import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemePreferences(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKey {
        val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        val USE_DYNAMIC_THEME = booleanPreferencesKey("use_dynamic_theme")
    }

    val themeMode: Flow<ThemeMode> = dataStore.data
        .map { preferences ->
            try {
                val themeModeString = preferences[THEME_MODE_KEY] ?: ThemeMode.SYSTEM.name
                ThemeMode.valueOf(themeModeString)
            } catch (e: IllegalArgumentException) {
                ThemeMode.SYSTEM
            }
        }

    val useDynamicTheme: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.USE_DYNAMIC_THEME] ?: true
        }

    suspend fun setThemeMode(themeMode: ThemeMode): EmptyResult<DataError.Local> {
        return try {
            dataStore.edit { preferences ->
                preferences[THEME_MODE_KEY] = themeMode.name
            }
            ResultWrapper.Success(Unit)
        } catch (e: IOException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    suspend fun setDynamicTheme(useDynamicTheme: Boolean): EmptyResult<DataError.Local> {
        return try {
            dataStore.edit { preferences ->
                preferences[PreferencesKey.USE_DYNAMIC_THEME] = useDynamicTheme
            }
            ResultWrapper.Success(Unit)
        } catch (e: IOException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }
}