package com.example.notes.ui.note.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notes.AppBar

@Composable
fun NoteDetailsScreen(
    noteId: Int,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
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