package com.example.notes.ui.home

import com.example.notes.database.Note
import com.example.notes.database.NotesColumn
import com.example.notes.database.SortDirection

data class HomeScreenUiState(
    val isLoading: Boolean = true,
    val notes: List<Note> = listOf(),
    var sortByColumn: NotesColumn = NotesColumn.TITLE,
    var titleSortDirection: SortDirection = SortDirection.ASCENDING,
    var timeStampSortDirection: SortDirection = SortDirection.ASCENDING,
    var viewMode: ViewMode = ViewMode.STAGGERED_GRID
)

enum class ViewMode { LINEAR_LIST, STAGGERED_GRID }
