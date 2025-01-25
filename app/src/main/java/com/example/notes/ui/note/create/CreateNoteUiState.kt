package com.example.notes.ui.note.create

import androidx.annotation.StringRes
import com.example.notes.database.Note

data class CreateNoteUiState(
    val titleText: String = "",
    val contentText: String = "",
    @StringRes val titleErrorMsgRes: Int? = null
)

fun CreateNoteUiState.toNote(): Note {
    return Note(
        title = titleText,
        content = contentText
    )
}