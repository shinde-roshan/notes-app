package com.example.notes.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.AppBar
import com.example.notes.R
import com.example.notes.database.Note
import com.example.notes.database.NotesColumn
import com.example.notes.database.SortDirection
import com.example.notes.ui.AppViewModelProvider
import com.example.notes.utils.toDateStr


@Composable
fun HomeScreen(
    navigateToNoteDetails: (Int) -> Unit,
    navigateToCreateNote: () -> Unit,
    viewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.homeScreenUiState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.app_name),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToCreateNote() }
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Add,
                    contentDescription = stringResource(R.string.create_new_note)
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            uiState.notes.isEmpty() -> {
                Text(
                    text = stringResource(R.string.empty_notes_msg),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                        .padding(
                            bottom = dimensionResource(R.dimen.dp_88),
                            start = dimensionResource(R.dimen.dp_16),
                            end = dimensionResource(R.dimen.dp_16)
                        )
                )
            }
            else -> {
                Column(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (uiState.notes.size > 1) {
                            SortButton(
                                label = stringResource(R.string.title),
                                isSelected = uiState.sortByColumn == NotesColumn.TITLE,
                                direction = uiState.titleSortDirection,
                                onClick = { viewModel.onSortButtonClicked(NotesColumn.TITLE) }
                            )
                            SortButton(
                                label = stringResource(R.string.date),
                                isSelected = uiState.sortByColumn == NotesColumn.TIMESTAMP,
                                direction = uiState.timeStampSortDirection,
                                onClick = { viewModel.onSortButtonClicked(NotesColumn.TIMESTAMP) }
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = { viewModel.toggleViewMode() }
                        ) {
                            Icon(
                                painter = when (uiState.viewMode) {
                                    ViewMode.LINEAR_LIST -> painterResource(R.drawable.ic_view_grid)
                                    else -> painterResource(R.drawable.ic_view_list)
                                },
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                contentDescription = when (uiState.viewMode) {
                                    ViewMode.LINEAR_LIST -> stringResource(R.string.grid_view)
                                    else -> stringResource(R.string.list_view)
                                }
                            )
                        }
                    }
                    Notes(
                        uiState = uiState,
                        onItemClicked = navigateToNoteDetails
                    )
                }
            }
        }
    }
}

@Composable
fun SortButton(
    label: String,
    isSelected: Boolean,
    direction: SortDirection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = { onClick() },
        colors = ButtonDefaults.outlinedButtonColors().copy(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
            .padding(dimensionResource(R.dimen.dp_8))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall
            )
            Icon(
                painter = if (direction == SortDirection.DESCENDING) painterResource(R.drawable.ic_arrow_down)
                else painterResource(R.drawable.ic_arrow_up),
                tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.dp_4))
            )
        }
    }
}

@Composable
fun Notes(
    uiState: HomeScreenUiState,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiState.viewMode == ViewMode.LINEAR_LIST) {
        LazyColumn(modifier = modifier) {
            items(uiState.notes) { note ->
                NoteItem(
                    note = note,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.dp_8))
                        .clickable { onItemClicked(note.id) }
                )
            }
        }
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = dimensionResource(R.dimen.dp_8),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dp_8)),
            modifier = modifier.padding(dimensionResource(R.dimen.dp_8))
        ) {
            items(uiState.notes) { note ->
                NoteItem(
                    note = note,
                    modifier = Modifier.clickable { onItemClicked(note.id) }
                )
            }
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