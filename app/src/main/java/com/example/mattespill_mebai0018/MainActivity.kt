package com.example.mattespill_mebai0018

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.mattespill_mebai0018.navigation.AppNavHost
import com.example.mattespill_mebai0018.ui.theme.MatteSpillMebai0018Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 🔹 Hent lagret språk før vi setter UI
        val prefs = getSharedPreferences("matte_prefs", Context.MODE_PRIVATE)
        val langCode = prefs.getString("language", "no") ?: "no"
        setAppLocale(this, langCode)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MatteSpillMebai0018Theme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
