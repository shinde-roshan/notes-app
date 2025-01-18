package com.example.notes.utils

import java.text.DateFormat
import java.util.Date
import java.util.Locale

fun toDateStr(milliSec: Long): String {
    return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH)
        .format(Date(milliSec))
}