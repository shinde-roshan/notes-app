package com.example.notes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNote(id: Int): Flow<Note>

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getNotesSortedByTitleAsc(): Flow<List<Note>>

    @Query("SELECT * FROM notes ORDER BY title DESC")
    fun getNotesSortedByTitleDesc(): Flow<List<Note>>

    @Query("SELECT * FROM notes ORDER BY timestamp ASC")
    fun getNotesSortedByTimestampAsc(): Flow<List<Note>>

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getNotesSortedByTimestampDesc(): Flow<List<Note>>
}