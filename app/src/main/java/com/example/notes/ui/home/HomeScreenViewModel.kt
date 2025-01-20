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

    private fun getNotes() {
        viewModelScope.launch {
            notesRepository.getNotes(
                column = _homeScreenUiState.value.sortByColumn,
                sortDirection = when (_homeScreenUiState.value.sortByColumn) {
                    NotesColumn.TIMESTAMP -> _homeScreenUiState.value.timeStampSortDirection
                    else -> _homeScreenUiState.value.titleSortDirection
                }
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
            when (column) {
                NotesColumn.TIMESTAMP -> {
                    _homeScreenUiState.value.timeStampSortDirection =
                        toggleSortDirection(_homeScreenUiState.value.timeStampSortDirection)
                }

                else -> {
                    _homeScreenUiState.value.titleSortDirection =
                        toggleSortDirection(_homeScreenUiState.value.titleSortDirection)
                }
            }
        } else _homeScreenUiState.value.sortByColumn = column
        getNotes()
    }

    private fun toggleSortDirection(currSortDirection: SortDirection): SortDirection {
        return when (currSortDirection) {
            SortDirection.ASCENDING -> SortDirection.DESCENDING
            else -> SortDirection.ASCENDING
        }
    }

    fun toggleViewMode() {
        _homeScreenUiState.value = _homeScreenUiState.value.copy(
            viewMode = when(_homeScreenUiState.value.viewMode) {
                ViewMode.STAGGERED_GRID -> ViewMode.LINEAR_LIST
                else -> ViewMode.STAGGERED_GRID
            }
        )
    }

}