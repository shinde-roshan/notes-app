package com.example.notes.ui.note.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NoteDetailsViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    private var noteId: Int? = null
    private val _noteDetailsUiState = MutableStateFlow(NoteDetailsUiState())
    val noteDetailsUiState: StateFlow<NoteDetailsUiState> = _noteDetailsUiState

    private fun getNote() {
        viewModelScope.launch {
            noteId?.let { id ->
                notesRepository.getNote(id)
                    .catch {
                        _noteDetailsUiState.value = _noteDetailsUiState.value.copy(
                            note = null
                        )
                    }
                    .collect {
                        _noteDetailsUiState.value = _noteDetailsUiState.value.copy(
                            note = it
                        )
                    }
            }

        }
    }

    fun setNoteId(id: Int) {
        noteId = id
        getNote()
    }
}