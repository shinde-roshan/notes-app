package com.example.notes.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class NoteDetails(val noteId: Int)

@Serializable
object EditNote