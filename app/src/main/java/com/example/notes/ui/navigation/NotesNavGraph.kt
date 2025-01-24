package com.example.notes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.notes.ui.home.HomeScreen
import com.example.notes.ui.note.create.NoteCreateScreen
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
                    navController.navigate(NoteCreate)
                }
            )
        }
        composable<NoteDetails> { navBackStackEntry ->
            val noteDetails: NoteDetails = navBackStackEntry.toRoute()
            NoteDetailsScreen(
                noteId = noteDetails.noteId,
                navigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<NoteCreate> {
            NoteCreateScreen(
                navigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}