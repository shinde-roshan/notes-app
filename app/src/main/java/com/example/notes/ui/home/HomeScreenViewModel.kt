package com.example.notes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeScreenViewModel(notesRepository: NotesRepository): ViewModel() {
    val homeScreenUiState: StateFlow<HomeScreenUiState> =
        notesRepository.getNotes().map { HomeScreenUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(3000L),
                initialValue = HomeScreenUiState()
            )

}