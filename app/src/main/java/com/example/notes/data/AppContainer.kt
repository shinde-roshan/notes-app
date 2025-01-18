package com.example.notes.data

import android.content.Context
import com.example.notes.database.NotesDatabase

/**
 * AppContainer for dependency injection
 */
interface AppContainer {
    val notesRepository: NotesRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val notesRepository: NotesRepository by lazy {
        NotesRepository(NotesDatabase.getInstance(context).notesDao())
    }
}