package com.example.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val color: String,
    val timestamp: Timestamp,
    @ColumnInfo(name = "remind_at") val remindAt: Timestamp,
    @ColumnInfo(name = "reminder_frequency") val reminderFrequency: ReminderFrequency
)
