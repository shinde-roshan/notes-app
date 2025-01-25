package com.example.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
    val title: String,
    val content: String,
    val color: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "remind_at") val remindAt: Long? = null,
    @ColumnInfo(name = "reminder_frequency") val reminderFrequency: ReminderFrequency? = null
)
