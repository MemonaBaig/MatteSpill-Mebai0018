
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mattespill_mebai0018.R
import com.example.mattespill_mebai0018.ui.components.AppButton
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource


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
        verticalArrangement = Arrangement.SpaceBetween // 👈 sprer innholdet ut
    ) {
        // 🦉 Uglen øverst
        Image(
            painter = painterResource(id = R.drawable.owl),
            contentDescription = "Uglen",
            modifier = Modifier
                .size(300.dp) // gjør uglen stor
                .padding(top = 16.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🔹 App-tittel
            Text(
                stringResource(R.string.app_title),
                fontSize = 40.sp,
                color = colorResource(id = R.color.accentOrange)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Highscore
            if (highscore > 0) {
                Text(
                    stringResource(R.string.best_score, highscore),
                    fontSize = 22.sp,
                    color = colorResource(id = R.color.primaryBlue)
                )
            } else {
                Text(
                    stringResource(R.string.no_score),
                    fontSize = 22.sp,
                    color = colorResource(id = R.color.accentYellow)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 🔹 Meny-knapper (nå større)
            AppButton(
                text = stringResource(R.string.start_game),
                icon = Icons.Default.PlayArrow,
                color = colorResource(id = R.color.primaryBlue),
                onClick = onStartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp) // 👈 større knapp
            )

            AppButton(
                text = stringResource(R.string.about_game),
                icon = Icons.Default.Info,
                color = colorResource(id = R.color.accentYellow),
                onClick = onAboutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )

            AppButton(
                text = stringResource(R.string.preferences),
                icon = Icons.Default.Settings,
                color = colorResource(id = R.color.accentOrange),
                onClick = onPrefsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
