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
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.app_title),
            fontSize = 36.sp,
            color = colorResource(id = R.color.accentOrange)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (highscore > 0) {
            Text(
                stringResource(R.string.best_score, highscore),
                fontSize = 20.sp,
                color = colorResource(id = R.color.primaryBlue)
            )
        } else {
            Text(
                stringResource(R.string.no_score),
                fontSize = 20.sp,
                color = colorResource(id = R.color.accentYellow)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Start spill-knapp
        Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.primaryBlue)
            )
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = stringResource(R.string.start_game))
            Spacer(Modifier.width(8.dp))
            Text(stringResource(R.string.start_game), fontSize = 20.sp)
        }

        // Om spillet-knapp
        Button(
            onClick = onAboutClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.accentYellow)
            )
        ) {
            Icon(Icons.Default.Info, contentDescription = stringResource(R.string.about_game))
            Spacer(Modifier.width(8.dp))
            Text(stringResource(R.string.about_game), fontSize = 20.sp)
        }

        // Preferanser-knapp
        Button(
            onClick = onPrefsClick,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.accentOrange)
            )
        ) {
            Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.preferences))
            Spacer(Modifier.width(8.dp))
            Text(stringResource(R.string.preferences), fontSize = 20.sp)
        }
    }
}


//Bruk av skerjemn, bevisst på fargevalg - vise forskjelliger og ulike funksjoner
// Ikke mye tekst - prøv å lage bedre knapper - bruk mer av skjermen , større knapper
//Større ikon
// trenger ikke skrive oppgave 1 - ditt svar - trenger ikke være, tekstfelt, tydelig å skrive inn noe
// oppgavene tallene bør ta mer plass, 0 trenger ikke mer plass
// bevisst forhold til hvordan man bruker skjermbildet, ta med uglen under hele spillet
// ditt svar- trenger ikke det, svarstallet må bli større
// lage kompoent mappe , gå igjennom filer for å komponenter fra alle skjermbilder
// lage tekstsfelt , skjermting for seg selv, ..