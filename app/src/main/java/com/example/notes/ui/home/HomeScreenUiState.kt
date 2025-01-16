package com.example.notes.ui.home

import com.example.notes.database.Note

data class HomeScreenUiState(
    val notes: List<Note> = listOf()
)