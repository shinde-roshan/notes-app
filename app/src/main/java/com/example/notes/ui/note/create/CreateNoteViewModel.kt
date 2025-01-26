package com.example.notes.ui.note.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.R
import com.example.notes.data.NotesRepository
import kotlinx.coroutines.launch

class CreateNoteViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    private var _createNoteUiState = mutableStateOf(CreateNoteUiState())
    val createNoteUiState: State<CreateNoteUiState> = _createNoteUiState

    fun onTitleTextChanged(text: String) {
        _createNoteUiState.value = _createNoteUiState.value.copy(
            titleText = text,
            titleErrorMsgRes = null
        )
    }

    fun onContentTextChanged(text: String) {
        _createNoteUiState.value = _createNoteUiState.value.copy(
            contentText = text
        )
    }

    private fun isInputValid(): Boolean {
        if (_createNoteUiState.value.titleText.isEmpty()) {
            _createNoteUiState.value = _createNoteUiState.value.copy(
                titleErrorMsgRes = R.string.title_empty_warning
            )
            return false
        }
        return true
    }

    fun saveNote(callback: () -> Unit) {
        if (isInputValid()) {
            val note = _createNoteUiState.value.toNote()
            viewModelScope.launch {
                notesRepository.insertNote(note)
                callback()
            }
        }
    }

}