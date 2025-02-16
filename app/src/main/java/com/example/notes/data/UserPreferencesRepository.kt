package com.example.notes.data

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.notes.ui.home.ViewMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        val VIEW_MODE_LAYOUT = stringPreferencesKey("view_mode_layout")
    }

    val viewModeLayout: Flow<ViewMode> = dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences())
            else throw e
        }
        .map { preferences ->
            ViewMode.valueOf(
                preferences[VIEW_MODE_LAYOUT] ?: ViewMode.STAGGERED_GRID.name
            )
        }

    suspend fun setViewModeLayout(viewMode: ViewMode) {
        dataStore.edit { preferences ->
            preferences[VIEW_MODE_LAYOUT] = viewMode.name
        }
    }


}