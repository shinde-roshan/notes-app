package com.example.notes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NotesRepository
import com.example.notes.database.NotesColumn
import com.example.notes.database.SortDirection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    private val _homeScreenUiState =
        MutableStateFlow<HomeScreenUiState>(HomeScreenUiState())
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState


    init {
        getNotes()
    }

    private fun getNotes()
    {
        viewModelScope.launch {
            notesRepository.getNotes(
                column = _homeScreenUiState.value.sortByColumn,
                sortDirection = _homeScreenUiState.value.sortDirection
            )
                .catch {
                    _homeScreenUiState.value = _homeScreenUiState.value.copy(
                        notes = listOf()
                    )
                }
                .collect { notes ->
                    _homeScreenUiState.value = _homeScreenUiState.value.copy(
                        notes = notes
                    )
                }
        }
    }

    fun onSortButtonClicked(column: NotesColumn) {
        if (column == _homeScreenUiState.value.sortByColumn) {
            if (_homeScreenUiState.value.sortDirection == SortDirection.ASCENDING) {
                _homeScreenUiState.value.sortDirection = SortDirection.DESCENDING
            } else _homeScreenUiState.value.sortDirection = SortDirection.ASCENDING
        } else _homeScreenUiState.value.sortByColumn = column
        getNotes()
    }

}