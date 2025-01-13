package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var instance: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, NotesDatabase::class.java, "notes_database")
                    .build()
                    .also { instance = it }
            }
        }
    }
}