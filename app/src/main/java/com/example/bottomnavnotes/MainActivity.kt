package com.example.bottomnavnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavApp()
        }
    }
}

@Composable
fun BottomNavApp() {
    val navController = rememberNavController()
    var notes by remember { mutableStateOf(listOf<Note>()) }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(notes = notes, onNoteClick = { note ->
                    navController.navigate(Screen.Detail.createRoute(note.title, note.content))
                })
            }
            composable(Screen.AddNote.route) {
                AddNoteScreen(onAddNote = { newNote ->
                    notes = notes + newNote
                    navController.navigate(Screen.Home.route)
                })
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("content") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                val content = backStackEntry.arguments?.getString("content") ?: ""
                DetailScreen(title, content)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(Screen.Home, Screen.AddNote)
    NavigationBar {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) },
                label = { Text(screen.title) },
                icon = {}
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
