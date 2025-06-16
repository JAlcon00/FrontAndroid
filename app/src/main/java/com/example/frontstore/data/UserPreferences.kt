package com.example.frontstore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(context: Context) {

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile("user_preferences") }
    )

    companion object {
        private val USER_ID_KEY = stringPreferencesKey("user_id")

        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(context: Context): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserPreferences(context).also { INSTANCE = it }
            }
        }
    }

    val userId: Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_ID_KEY]
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    suspend fun clearUserId() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
        }
    }
}