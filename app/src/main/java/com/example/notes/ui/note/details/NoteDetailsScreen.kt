package com.example.notes.ui.note.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.AppBar
import com.example.notes.ui.AppViewModelProvider

@Composable
fun NoteDetailsScreen(
    noteId: Int,
    navigateUp: () -> Unit,
    viewModel: NoteDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    viewModel.setNoteId(noteId)
    val uiState = viewModel.noteDetailsUiState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                title = "",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Text(
            text = "Note Details: $noteId",
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}