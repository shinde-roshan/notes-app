package com.example.notes.ui.note.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.notes.data.NotesRepository

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
}