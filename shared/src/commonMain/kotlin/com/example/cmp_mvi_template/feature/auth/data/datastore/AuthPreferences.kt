package com.example.cmp_mvi_template.feature.auth.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    private object Keys {
        val ACCESS_TOKEN = stringPreferencesKey("auth_access_token")
        val USER_ID = intPreferencesKey("auth_user_id")
        val USERNAME = stringPreferencesKey("auth_username")
        val EMAIL = stringPreferencesKey("auth_email")
        val FIRST_NAME = stringPreferencesKey("auth_first_name")
        val LAST_NAME = stringPreferencesKey("auth_last_name")
        val GENDER = stringPreferencesKey("auth_gender")
        val IMAGE = stringPreferencesKey("auth_image")
    }

    val accessToken: Flow<String?> = dataStore.data.map { preferences ->
        preferences[Keys.ACCESS_TOKEN]
    }

    val authUser: Flow<AuthUser?> = dataStore.data.map { preferences ->
        val id = preferences[Keys.USER_ID] ?: return@map null
        val username = preferences[Keys.USERNAME] ?: return@map null
        val email = preferences[Keys.EMAIL] ?: return@map null
        val firstName = preferences[Keys.FIRST_NAME] ?: ""
        val lastName = preferences[Keys.LAST_NAME] ?: ""
        val gender = preferences[Keys.GENDER] ?: ""
        val image = preferences[Keys.IMAGE] ?: ""

        AuthUser(
            id = id,
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            image = image
        )
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map { preferences ->
        !preferences[Keys.ACCESS_TOKEN].isNullOrBlank()
    }

    suspend fun saveAuthSession(token: String, user: AuthUser) {
        dataStore.edit { preferences ->
            preferences[Keys.ACCESS_TOKEN] = token
            preferences[Keys.USER_ID] = user.id
            preferences[Keys.USERNAME] = user.username
            preferences[Keys.EMAIL] = user.email
            preferences[Keys.FIRST_NAME] = user.firstName
            preferences[Keys.LAST_NAME] = user.lastName
            preferences[Keys.GENDER] = user.gender
            preferences[Keys.IMAGE] = user.image
        }
    }

    suspend fun clearAuthSession() {
        dataStore.edit { preferences ->
            preferences.remove(Keys.ACCESS_TOKEN)
            preferences.remove(Keys.USER_ID)
            preferences.remove(Keys.USERNAME)
            preferences.remove(Keys.EMAIL)
            preferences.remove(Keys.FIRST_NAME)
            preferences.remove(Keys.LAST_NAME)
            preferences.remove(Keys.GENDER)
            preferences.remove(Keys.IMAGE)
        }
    }
}
