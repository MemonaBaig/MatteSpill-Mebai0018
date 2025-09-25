package com.example.mattespill_mebai0018.navigation




import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.mattespill_mebai0018.ui.screens.GameScreen
import com.example.mattespill_mebai0018.ui.screens.MenuScreen
import com.example.mattespill_mebai0018.ui.screens.OmSpilletScreen
import com.example.mattespill_mebai0018.ui.screens.PreferencesScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    // 🔹 Definerer navigasjonen mellom skjermene i appen
    NavHost(
        navController = navController,
        startDestination = Screen.Menu.route // Startskjerm = meny
    ) {
        // Meny-skjermen
        composable(Screen.Menu.route) {
            MenuScreen(
                onStartClick = { navController.navigate(Screen.Game.route) },
                onAboutClick = { navController.navigate(Screen.About.route) },
                onPrefsClick = { navController.navigate(Screen.Preferences.route) }
            )
        }

        // Spill-skjermen
        composable(Screen.Game.route) {
            GameScreen(onBack = { navController.popBackStack() })
        }

        // Om spillet-skjermen
        composable(Screen.About.route) {
            OmSpilletScreen(onBack = { navController.popBackStack() })
        }

        // Preferanser-skjermen
        composable(Screen.Preferences.route) {
            PreferencesScreen(onBack = { navController.popBackStack() })
        }
    }
}
