package com.example.notes.data

import com.example.notes.database.Note
import com.example.notes.database.NotesDao
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NotesDao) {
    fun getNotes(): Flow<List<Note>> = notesDao.getNotes()

    fun getNote(id: Int): Flow<Note> = notesDao.getNote(id)

    suspend fun insertNote(note: Note) = notesDao.insert(note)

    suspend fun updateNote(note: Note) = notesDao.update(note)

    suspend fun deleteNote(note: Note) = notesDao.delete(note)
}