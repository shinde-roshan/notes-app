package com.example.notes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel(notesRepository: NotesRepository): ViewModel() {
    private val _homeScreenUiState =
        MutableStateFlow<HomeScreenUiState>(HomeScreenUiState())
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState


    init {
        viewModelScope.launch {
            notesRepository.getNotes()
                .catch {
                    _homeScreenUiState.value = HomeScreenUiState()
                }
                .collect { notes ->
                    _homeScreenUiState.value = HomeScreenUiState(notes)
                }
        }
    }

}