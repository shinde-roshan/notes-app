package com.example.notes.data

import com.example.notes.database.Note
import com.example.notes.database.NotesColumn
import com.example.notes.database.NotesDao
import com.example.notes.database.SortDirection
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NotesDao) {
    fun getNotes(
        column: NotesColumn = NotesColumn.TITLE,
        sortDirection: SortDirection = SortDirection.ASCENDING
    ): Flow<List<Note>> {
        return when (column) {
            NotesColumn.TITLE -> {
                if (sortDirection == SortDirection.DESCENDING) {
                    notesDao.getNotesSortedByTitleDesc()
                } else {
                    notesDao.getNotesSortedByTitleAsc()
                }
            }

            NotesColumn.TIMESTAMP -> {
                if (sortDirection == SortDirection.DESCENDING) {
                    notesDao.getNotesSortedByTimestampDesc()
                } else {
                    notesDao.getNotesSortedByTimestampAsc()
                }
            }

            else -> notesDao.getNotesSortedByTitleAsc() // Default fallback
        }
    }

    fun getNote(id: Int): Flow<Note> = notesDao.getNote(id)

    suspend fun insertNote(note: Note) = notesDao.insert(note)

    suspend fun updateNote(note: Note) = notesDao.update(note)

    suspend fun deleteNote(note: Note) = notesDao.delete(note)
}