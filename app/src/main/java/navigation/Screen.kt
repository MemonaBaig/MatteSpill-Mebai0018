package com.example.mattespill_mebai0018.navigation
sealed class Screen(val route: String) {
    object Menu : Screen("menu")
    object Game : Screen("game")
    object About : Screen("about")
    object Preferences : Screen("preferences")
}
