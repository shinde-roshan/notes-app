package com.example.notes.data

import com.example.notes.database.Note
import com.example.notes.database.NotesDao
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val newsDao: NotesDao) {
    fun getNotes(): Flow<List<Note>> = newsDao.getNotes()

    fun getNote(id: Int): Flow<Note> = newsDao.getNote(id)

    suspend fun insertNote(note: Note) = newsDao.insert(note)

    suspend fun updateNote(note: Note) = newsDao.update(note)

    suspend fun deleteNote(note: Note) = newsDao.delete(note)
}