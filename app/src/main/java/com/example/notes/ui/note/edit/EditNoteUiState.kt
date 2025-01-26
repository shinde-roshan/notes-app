package com.example.notes.ui.note.edit

import androidx.annotation.StringRes
import com.example.notes.database.Note

data class EditNoteUiState(
    val titleText: String = "",
    val contentText: String = "",
    @StringRes val titleErrorMsgRes: Int? = null
)

fun EditNoteUiState.toNote(note: Note? = null): Note {
    return note?.copy(
        title = titleText,
        content = contentText
    ) ?: Note(
        title = titleText,
        content = contentText
    )
}