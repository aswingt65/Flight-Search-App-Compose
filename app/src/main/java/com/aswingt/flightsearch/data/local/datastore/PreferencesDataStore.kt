package com.aswingt.flightsearch.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val SEARCH_PREFERENCE_NAME = "search_preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SEARCH_PREFERENCE_NAME)

class PreferencesDataStore(private val dataStore: DataStore<Preferences>) : LocalSearchDataSource {

    companion object {
        val SEARCH_QUERY = stringPreferencesKey("search_query")
    }

    override suspend fun saveSearchQuery(query: String): Boolean {
        return try {
            dataStore.edit { preferences ->
                preferences[SEARCH_QUERY] = query
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun getSearchQueryStream(): Flow<String> =
        dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preferences ->
                preferences[SEARCH_QUERY] ?: ""
            }
}