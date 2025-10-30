package com.example.bottomnavnotes

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object AddNote : Screen("add_note", "Add Note")
    object Detail : Screen("detail/{title}/{content}", "Detail") {
        fun createRoute(title: String, content: String): String {
            return "detail/${title}/${content}"
        }
    }
}
