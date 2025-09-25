package com.example.mattespill_mebai0018.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // 👇 Mer luft på toppen
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                stringResource(R.string.prefs_title),
                fontSize = 34.sp,
                color = colorResource(id = R.color.accentOrange)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 🔢 Antall oppgaver
            Text(
                stringResource(R.string.prefs_questions),
                fontSize = 24.sp,
                color = colorResource(id = R.color.accentRed)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf(
                    5 to R.color.primaryBlue,
                    10 to R.color.green,
                    15 to R.color.accentOrange
                ).forEach { (number, color) ->
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
                        },
                        selectedColor = colorResource(id = color),
                        modifier = Modifier.weight(1f).height(70.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                stringResource(R.string.prefs_selected_questions, totalQuestions),
                fontSize = 20.sp,
                color = colorResource(id = R.color.owlBrown)
            )

            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage!!,
                    color = colorResource(id = R.color.accentRed),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 🌐 Språk
            Text(
                stringResource(R.string.prefs_language),
                fontSize = 24.sp,
                color = colorResource(id = R.color.accentRed)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("norsk" to "no", "deutsch" to "de").forEach { (label, code) ->
                    SelectableButton(
                        text = label,
                        isSelected = languageCode == code,
                        onClick = {
                            prefViewModel.setLanguage(code)
                            setAppLocale(context, code)
                            (context as? Activity)?.recreate()
                        },
                        modifier = Modifier.weight(1f).height(70.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 🎚️ Vanskelighetsgrad
            Text(
                stringResource(R.string.prefs_difficulty),
                fontSize = 24.sp,
                color = colorResource(id = R.color.accentRed)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                listOf(
                    stringResource(R.string.easy) to ("lett" to R.color.primaryBlue),
                    stringResource(R.string.hard) to ("vanskelig" to R.color.green),
                    stringResource(R.string.very_hard) to ("veldig vanskelig" to R.color.accentRed)
                ).forEach { (label, pair) ->
                    val (level, color) = pair
                    SelectableButton(
                        text = label,
                        isSelected = difficulty == level,
                        onClick = { prefViewModel.setDifficulty(level) },
                        selectedColor = colorResource(id = color),
                        modifier = Modifier.fillMaxWidth().height(70.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            BackButton(onClick = onBack)
        }

        // 🦉 Uglen nederst
        Image(
            painter = painterResource(id = R.drawable.owl),
            contentDescription = "Uglen",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
