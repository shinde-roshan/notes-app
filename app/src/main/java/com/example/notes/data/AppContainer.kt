package com.example.notes.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.notes.database.NotesDatabase

/**
 * AppContainer for dependency injection
 */
interface AppContainer {
    val notesRepository: NotesRepository
    val userPreferencesRepository: UserPreferencesRepository
}

private const val NOTES_USER_PREF_NAME = "notes_user_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = NOTES_USER_PREF_NAME
)

class AppDataContainer(private val context: Context): AppContainer {
    override val notesRepository: NotesRepository by lazy {
        NotesRepository(NotesDatabase.getInstance(context).notesDao())
    }
    override val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(context.dataStore)
    }
}