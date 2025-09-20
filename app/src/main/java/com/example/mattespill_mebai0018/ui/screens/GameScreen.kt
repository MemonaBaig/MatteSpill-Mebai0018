package com.example.mattespill_mebai0018.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mattespill_mebai0018.R

@Composable
fun GameScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("matte_prefs", Context.MODE_PRIVATE)

    // Vanskelighetsgrad
    val difficulty = prefs.getString("difficulty", "lett")

    val tasks = when (difficulty) {
        "vanskelig" -> context.resources.getStringArray(R.array.tasks_medium).toList()
        "veldig vanskelig" -> context.resources.getStringArray(R.array.tasks_hard).toList()
        else -> context.resources.getStringArray(R.array.tasks_easy).toList()
    }

    val answers = when (difficulty) {
        "vanskelig" -> context.resources.getStringArray(R.array.answers_medium).toList()
        "veldig vanskelig" -> context.resources.getStringArray(R.array.answers_hard).toList()
        else -> context.resources.getStringArray(R.array.answers_easy).toList()
    }

    val totalQuestions = prefs.getInt("total_questions", 5)

    // 🔹 Lag liste med oppgaver + svar, randomisert
    val questionList = remember {
        tasks.zip(answers).shuffled().take(totalQuestions)
    }

    var currentIndex by remember { mutableStateOf(0) }
    var userInput by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }
    var finished by remember { mutableStateOf(false) }
    var answered by remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }
    var newHighscore by remember { mutableStateOf(false) }

    // Tekster fra strings.xml
    val gameOverText = stringResource(id = R.string.game_over)
    val scoreText = stringResource(id = R.string.score)
    val backText = stringResource(id = R.string.back)
    val taskTitleText = stringResource(id = R.string.task_title)
    val yourAnswerText = stringResource(id = R.string.your_answer)
    val deleteText = stringResource(id = R.string.delete)
    val checkAnswerText = stringResource(id = R.string.check_answer)
    val correctText = stringResource(id = R.string.correct)
    val wrongText = stringResource(id = R.string.wrong)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 🔹 Tilbake-knapp med ikon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { showExitDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = backText)
                Spacer(modifier = Modifier.width(4.dp))
                Text(backText)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (totalQuestions > tasks.size) {
            Text(
                "Det finnes ikke nok oppgaver på dette nivået.\nVelg færre oppgaver i preferanser.",
                fontSize = 20.sp
            )
        } else if (finished) {
            // Spill ferdig → sjekk og lagre highscore
            val currentHighscore = prefs.getInt("highscore", 0)
            if (score > currentHighscore) {
                prefs.edit().putInt("highscore", score).apply()
                newHighscore = true
            }
            val highscore = prefs.getInt("highscore", 0)

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(gameOverText, fontSize = 28.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("$scoreText: $score / $totalQuestions", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Beste resultat: $highscore", fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)

                if (newHighscore) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("🎉 Ny rekord! 🎉", fontSize = 26.sp, color = MaterialTheme.colorScheme.tertiary)
                }
            }
        } else {
            val (task, correctAnswer) = questionList[currentIndex]

            Text("$taskTitleText ${currentIndex + 1} / $totalQuestions", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(task, fontSize = 36.sp)

            Spacer(modifier = Modifier.height(24.dp))
            Text("$yourAnswerText $userInput", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(16.dp))

            // Tallknapper vises bare hvis oppgaven ikke er sjekket
            if (!answered) {
                Column {
                    for (row in listOf(
                        listOf("1", "2", "3"),
                        listOf("4", "5", "6"),
                        listOf("7", "8", "9"),
                        listOf("0")
                    )) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth().padding(4.dp)
                        ) {
                            row.forEach { digit ->
                                Button(
                                    onClick = { userInput += digit },
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .weight(1f)
                                        .height(80.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Text(digit, fontSize = 28.sp)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Slette knapp
                Button(
                    onClick = {
                        if (userInput.isNotEmpty()) {
                            userInput = userInput.dropLast(1)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text(deleteText, fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sjekk svar knapp
                Button(
                    onClick = {
                        answered = true
                        if (userInput == correctAnswer) {
                            feedback = "$correctText $task = $correctAnswer"
                            score++
                        } else {
                            feedback = "$wrongText $task = $correctAnswer"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                ) {
                    Text(checkAnswerText, fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(feedback, fontSize = 22.sp)

            // Neste oppgave vises først etter at man har svart
            if (answered) {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        if (currentIndex + 1 < totalQuestions) {
                            currentIndex++
                            userInput = ""
                            feedback = ""
                            answered = false
                        } else {
                            finished = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Neste oppgave", fontSize = 20.sp)
                }
            }
        }
    }

    // 🔹 Dialogboksen
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Avslutte spill?") },
            text = { Text("Er du sikker på at du vil avslutte spillet?") },
            confirmButton = {
                Button(onClick = {
                    showExitDialog = false
                    onBack()
                }) {
                    Text("Ja")
                }
            },
            dismissButton = {
                Button(onClick = { showExitDialog = false }) {
                    Text("Nei")
                }
            }
        )
    }
}
