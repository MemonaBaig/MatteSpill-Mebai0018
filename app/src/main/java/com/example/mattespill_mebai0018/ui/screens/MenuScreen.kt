package com.example.mattespill_mebai0018.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuScreen(
    onStartClick: () -> Unit,
    onAboutClick: () -> Unit,
    onPrefsClick: () -> Unit
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("matte_prefs", Context.MODE_PRIVATE)
    val highscore = prefs.getInt("highscore", 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🎲 MatteSpill 🎲", fontSize = 36.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Vis highscore eller melding om ingen rekord
        if (highscore > 0) {
            Text(
                "⭐ Beste resultat: $highscore",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                "Ingen rekord ennå",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Start spill-knapp
        Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = "Start spill")
            Spacer(Modifier.width(8.dp))
            Text("Start spill", fontSize = 20.sp)
        }

        // Om spillet-knapp
        Button(
            onClick = onAboutClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Icon(Icons.Default.Info, contentDescription = "Om spillet")
            Spacer(Modifier.width(8.dp))
            Text("Om spillet", fontSize = 20.sp)
        }

        // Preferanser-knapp
        Button(
            onClick = onPrefsClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(Icons.Default.Settings, contentDescription = "Preferanser")
            Spacer(Modifier.width(8.dp))
            Text("Preferanser", fontSize = 20.sp)
        }
    }
}
