package com.example.notes.ui.note.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.R
import com.example.notes.data.NotesRepository
import com.example.notes.database.Note
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val notesRepository: NotesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteId: Int? = savedStateHandle["noteId"]
    private var note: Note? = null

    private var _editNoteUiState = mutableStateOf(EditNoteUiState())
    val editNoteUiState: State<EditNoteUiState> = _editNoteUiState

    init {
        if (noteId != null) {
            getNote()
        }
    }

    fun onTitleTextChanged(text: String) {
        _editNoteUiState.value = _editNoteUiState.value.copy(
            titleText = text,
            titleErrorMsgRes = null
        )
    }

    fun onContentTextChanged(text: String) {
        _editNoteUiState.value = _editNoteUiState.value.copy(
            contentText = text
        )
    }

    private fun isInputValid(): Boolean {
        if (_editNoteUiState.value.titleText.isEmpty()) {
            _editNoteUiState.value = _editNoteUiState.value.copy(
                titleErrorMsgRes = R.string.title_empty_warning
            )
            return false
        }
        return true
    }

    fun saveNote(callback: () -> Unit) {
        if (isInputValid()) {
            val noteToSave = _editNoteUiState.value.toNote(note)
            viewModelScope.launch {
                if (note != null) notesRepository.updateNote(noteToSave)
                else notesRepository.insertNote(noteToSave)
                callback()
            }
        }
    }

    private fun getNote() {
        viewModelScope.launch {
            noteId?.let { id ->
                notesRepository.getNote(id)
                    .catch {
                        note = null
                        _editNoteUiState.value = _editNoteUiState.value.copy(
                            titleText = "",
                            contentText = "",
                            titleErrorMsgRes = null
                        )
                    }
                    .collect {
                        note = it
                        _editNoteUiState.value = _editNoteUiState.value.copy(
                            titleText = note!!.title,
                            contentText = note!!.content,
                            titleErrorMsgRes = null
                        )
                    }
            }
        }
    }

}