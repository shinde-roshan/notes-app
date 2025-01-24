package com.example.notes.ui.note.create

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.notes.R

@Composable
fun NoteCreateScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    Scaffold (
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Text(
            text = "Create New Note",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.dp_16))
        )
    }
}