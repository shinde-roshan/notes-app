package com.example.notes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.notes.ui.home.HomeScreen
import com.example.notes.ui.note.edit.EditNoteScreen
import com.example.notes.ui.note.details.NoteDetailsScreen

@Composable
fun NotesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            HomeScreen(
                navigateToNoteDetails = { noteId ->
                    navController.navigate(NoteDetails(noteId))
                },
                navigateToCreateNote = {
                    navController.navigate(EditNote())
                }
            )
        }
        composable<NoteDetails> { navBackStackEntry ->
            val noteDetails: NoteDetails = navBackStackEntry.toRoute()
            NoteDetailsScreen(
                noteId = noteDetails.noteId,
                navigateToEditNote = { noteId -> navController.navigate(EditNote(noteId)) },
                navigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<EditNote> { navBackStackEntry ->
            val editNote: EditNote = navBackStackEntry.toRoute()
            EditNoteScreen(
                noteId = editNote.noteId,
                navigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}