package com.example.notes.database

import androidx.room.TypeConverter

class TypeConverter {
    @TypeConverter
    fun fromReminderFrequency(value: ReminderFrequency?): String? {
        return value?.name
    }

    @TypeConverter
    fun toReminderFrequency(value: String?): ReminderFrequency? {
        return value?.let { ReminderFrequency.valueOf(it) }
    }
}