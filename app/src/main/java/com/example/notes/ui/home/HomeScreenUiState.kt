package com.example.notes.ui.home

import com.example.notes.database.Note
import com.example.notes.database.NotesColumn
import com.example.notes.database.SortDirection

data class HomeScreenUiState(
    val notes: List<Note> = listOf(),
    var sortByColumn: NotesColumn = NotesColumn.TITLE,
    var sortDirection: SortDirection = SortDirection.ASCENDING
)