package com.example.mattespill_mebai0018.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mattespill_mebai0018.R
import com.example.mattespill_mebai0018.setAppLocale
import com.example.mattespill_mebai0018.ui.viewmodel.PrefViewModel
import com.example.mattespill_mebai0018.ui.components.BackButton
import com.example.mattespill_mebai0018.ui.components.SelectableButton

@Composable
fun PreferencesScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val prefViewModel: PrefViewModel = viewModel()

    val totalQuestions by prefViewModel.totalQuestions
    val languageCode by prefViewModel.languageCode
    val difficulty by prefViewModel.difficulty

    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            stringResource(R.string.prefs_title),
            fontSize = 28.sp,
            color = colorResource(id = R.color.accentOrange)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🔢 Antall oppgaver
        Text(
            stringResource(R.string.prefs_questions),
            fontSize = 20.sp,
            color = colorResource(id = R.color.accentRed)
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf(5, 10, 15).forEach { number ->
                SelectableButton(
                    text = number.toString(),
                    isSelected = totalQuestions == number,
                    onClick = {
                        val maxTasks = when (difficulty) {
                            "vanskelig" -> context.resources.getStringArray(R.array.tasks_medium).size
                            "veldig vanskelig" -> context.resources.getStringArray(R.array.tasks_hard).size
                            else -> context.resources.getStringArray(R.array.tasks_easy).size
                        }

                        if (number > maxTasks) {
                            errorMessage = "Opps! Det finnes bare $maxTasks oppgaver for dette nivået!"
                        } else {
                            errorMessage = null
                            prefViewModel.setTotalQuestions(number)
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            stringResource(R.string.prefs_selected_questions, totalQuestions),
            color = colorResource(id = R.color.owlBrown)
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = colorResource(id = R.color.accentRed),
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 🌐 Språk
        Text(
            stringResource(R.string.prefs_language),
            fontSize = 20.sp,
            color = colorResource(id = R.color.accentRed)
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("norsk" to "no", "deutsch" to "de").forEach { (label, code) ->
                SelectableButton(
                    text = label,
                    isSelected = languageCode == code,
                    onClick = {
                        prefViewModel.setLanguage(code)
                        setAppLocale(context, code)
                        (context as? Activity)?.recreate()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            stringResource(
                R.string.prefs_selected_language,
                if (languageCode == "no") "norsk" else "deutsch"
            ),
            color = colorResource(id = R.color.owlBrown)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🎚️ Vanskelighetsgrad
        Text(
            stringResource(R.string.prefs_difficulty),
            fontSize = 20.sp,
            color = colorResource(id = R.color.accentRed)
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf(
                stringResource(R.string.easy) to "lett",
                stringResource(R.string.hard) to "vanskelig",
                stringResource(R.string.very_hard) to "veldig vanskelig"
            ).forEach { (label, level) ->
                SelectableButton(
                    text = label,
                    isSelected = difficulty == level,
                    onClick = { prefViewModel.setDifficulty(level) }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            stringResource(R.string.prefs_selected_difficulty, difficulty),
            color = colorResource(id = R.color.owlBrown)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔙 Tilbake-knapp
        BackButton(onClick = onBack)
    }
}
