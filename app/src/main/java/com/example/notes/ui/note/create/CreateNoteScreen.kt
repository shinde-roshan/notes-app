package com.example.notes.ui.note.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.AppBar
import com.example.notes.R
import com.example.notes.ui.AppViewModelProvider

@Composable
fun CreateNoteScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    navigateBack: () -> Unit = {},
    viewModel: CreateNoteViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.createNoteUiState.value

    Scaffold(
        topBar = {
            AppBar(
                title = "",
                canNavigateBack = true,
                navigateUp = navigateUp,
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.TwoTone.Done,
                            contentDescription = stringResource(R.string.done)
                        )
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(state = rememberScrollState())
                .imePadding()
        ) {
            NoteTitle(
                text = uiState.titleText,
                errorMessage = uiState.titleErrorMsg,
                onTextChanged = { newText -> viewModel.onTitleTextChanged(newText) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.dp_8))
            )
            NoteContent(
                text = uiState.contentText,
                onTextChanged = { newText -> viewModel.onContentTextChanged(newText) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = dimensionResource(R.dimen.dp_8),
                        end = dimensionResource(R.dimen.dp_8),
                        bottom = dimensionResource(R.dimen.dp_8),
                    )
            )
        }
    }
}

@Composable
fun NoteTitle(
    text: String,
    errorMessage: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TextField(
            value = text,
            onValueChange = { newText -> onTextChanged(newText) },
            placeholder = {
                Text(
                    text = stringResource(R.string.note_title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.typography.headlineSmall.color.copy(
                        alpha = 0.3f
                    )
                )
            },
            isError = errorMessage.isNotEmpty(),
            textStyle = MaterialTheme.typography.headlineSmall,
            colors = TextFieldDefaults
                .colors()
                .copy(
                    focusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = MaterialTheme.colorScheme.errorContainer
                ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.dp_4))
            )
        }
    }
}

@Composable
fun NoteContent(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = { newText -> onTextChanged(newText) },
        placeholder = {
            Text(
                text = stringResource(R.string.type_something),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.typography.headlineSmall.color.copy(
                    alpha = 0.3f
                )
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults
            .colors()
            .copy(
                focusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        modifier = modifier
    )
}