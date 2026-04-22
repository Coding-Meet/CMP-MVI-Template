package com.example.cmp_mvi_template.core.data.datastore.onboarding

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class OnboardingPreferences(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKey {
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }

    val isOnboardingCompleted: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKey.ONBOARDING_COMPLETED] ?: false
        }

    suspend fun setOnboardingCompleted() {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.ONBOARDING_COMPLETED] = true
        }
    }
}