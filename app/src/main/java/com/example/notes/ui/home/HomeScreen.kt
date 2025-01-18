package com.example.notes.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.R
import com.example.notes.database.Note
import com.example.notes.ui.AppViewModelProvider
import com.example.notes.utils.toDateStr

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.homeScreenUiState.collectAsState()
    Notes(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
fun Notes(
    uiState: HomeScreenUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(uiState.notes) { note ->
            NoteItem(
                note = note,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.dp_8))
            )
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dp_16))
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.dp_4),
                    bottom = dimensionResource(R.dimen.dp_4)
                )
            )
            Text(
                text = toDateStr(note.timestamp),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.5f
                    )
                ),
                modifier = Modifier
                    .align(Alignment.End)
            )

        }
    }
}