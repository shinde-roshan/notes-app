package com.example.notes.ui.note.create

import androidx.annotation.StringRes

data class CreateNoteUiState(
    val titleText: String = "",
    val contentText: String = "",
    @StringRes val titleErrorMsgRes: Int? = null
)
