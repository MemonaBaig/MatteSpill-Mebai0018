package com.example.mattespill_mebai0018.ui.screens

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mattespill_mebai0018.R
import com.example.mattespill_mebai0018.setAppLocale

// Nøkkelord for SharedPreferences
private const val PREFS_NAME = "matte_prefs"
private const val KEY_TOTAL_QUESTIONS = "total_questions"
private const val KEY_LANGUAGE = "language"
private const val KEY_DIFFICULTY = "difficulty"

@Composable
fun PreferencesScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Hent lagrede verdier eller sett standard
    var totalQuestions by remember { mutableStateOf(prefs.getInt(KEY_TOTAL_QUESTIONS, 5)) }
    var languageCode by remember { mutableStateOf(prefs.getString(KEY_LANGUAGE, "no") ?: "no") }
    var difficulty by remember { mutableStateOf(prefs.getString(KEY_DIFFICULTY, "lett") ?: "lett") }

    // 🔹 Hent strenger fra strings.xml
    val prefsTitle = stringResource(id = R.string.prefs_title)
    val prefsQuestions = stringResource(id = R.string.prefs_questions)
    val prefsLanguage = stringResource(id = R.string.prefs_language)
    val prefsSelectedQuestions = stringResource(id = R.string.prefs_selected_questions, totalQuestions)
    val prefsSelectedLanguage = stringResource(
        id = R.string.prefs_selected_language,
        if (languageCode == "no") "norsk" else "tysk"
    )
    val backText = stringResource(id = R.string.back)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(prefsTitle, fontSize = 28.sp)
        Spacer(modifier = Modifier.height(24.dp))

        // Velg antall oppgaver
        Text(prefsQuestions, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf(5, 10, 15).forEach { number ->
                Button(
                    onClick = {
                        totalQuestions = number
                        prefs.edit().putInt(KEY_TOTAL_QUESTIONS, number).apply()
                    },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (totalQuestions == number)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(number.toString())
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(prefsSelectedQuestions)

        Spacer(modifier = Modifier.height(32.dp))

        // Velg språk
        Text(prefsLanguage, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("norsk" to "no", "tysk" to "de").forEach { (label, code) ->
                Button(
                    onClick = {
                        languageCode = code
                        prefs.edit().putString(KEY_LANGUAGE, code).apply()
                        setAppLocale(context, code)
                        (context as? Activity)?.recreate()
                    },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (languageCode == code)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(label)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(prefsSelectedLanguage)

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 Velg vanskelighetsgrad
        Text("Velg vanskelighetsgrad:", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("lett", "vanskelig", "veldig vanskelig").forEach { level ->
                Button(
                    onClick = {
                        difficulty = level
                        prefs.edit().putString(KEY_DIFFICULTY, level).apply()
                    },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (difficulty == level)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(level)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Valgt vanskelighetsgrad: $difficulty")

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onBack) {
            Text(backText)
        }
    }
}


// husk å ha deafault verdi - vet ikke om jeg har gjort det
// perfview model - tror prefenrecesns akl dit? idk
// legge til biblotek