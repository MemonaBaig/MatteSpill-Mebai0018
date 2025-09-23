package com.example.mattespill_mebai0018.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mattespill_mebai0018.R
import com.example.mattespill_mebai0018.ui.ViewModels.GameViewModel

@Composable
fun GameScreen(onBack: () -> Unit) {
    val gameViewModel: GameViewModel = viewModel()

    val currentIndex by gameViewModel.currentIndex
    val userInput by gameViewModel.userInput
    val feedback by gameViewModel.feedback
    val score by gameViewModel.score
    val finished by gameViewModel.finished
    val answered by gameViewModel.answered
    val showExitDialog by gameViewModel.showExitDialog
    val newHighscore by gameViewModel.newHighscore
    val errorMessage by gameViewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 🔙 Tilbake-knapp
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Button(
                onClick = { gameViewModel.showExitDialog() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.accentOrange)
                )
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(stringResource(R.string.back))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))



        if (errorMessage != null) {
            // 🔴 Vis feilmelding hvis ikke nok oppgaver
            Text(
                text = errorMessage ?: "",
                fontSize = 20.sp,
                color = colorResource(id = R.color.accentRed)
            )


        } else if (finished) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(stringResource(R.string.game_over), fontSize = 28.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "${stringResource(R.string.score)}: $score",
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (newHighscore) {
                    Text(
                        stringResource(R.string.new_highscore),
                        fontSize = 26.sp,
                        color = colorResource(id = R.color.accentYellow)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 🔙 Ny knapp for å gå tilbake uten “er du sikker?”
                Button(
                    onClick = { onBack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primaryBlue)
                    )
                ) {
                    Text(stringResource(R.string.back))
                }
            }
        }
        else {
            val (task, correctAnswer) = gameViewModel.currentTask()

            Text(
                "${stringResource(R.string.task_title)} ${currentIndex + 1}",
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(task, fontSize = 36.sp, color = colorResource(id = R.color.primaryBlue))

            Spacer(modifier = Modifier.height(24.dp))
            Text("${stringResource(R.string.your_answer)} $userInput", fontSize = 22.sp)

            Spacer(modifier = Modifier.height(16.dp))

            if (!answered) {
                // 🔢 Tallknapper
                Column {
                    for (row in listOf(
                        listOf("1", "2", "3"),
                        listOf("4", "5", "6"),
                        listOf("7", "8", "9"),
                        listOf("0")
                    )) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            row.forEach { digit ->
                                Button(
                                    onClick = { gameViewModel.addDigit(digit) },
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .weight(1f)
                                        .height(80.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(id = R.color.primaryBlue)
                                    )
                                ) {
                                    Text(digit, fontSize = 28.sp)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ❌ Slette-knapp
                Button(
                    onClick = { gameViewModel.deleteLastDigit() },
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.accentRed))
                ) {
                    Text(stringResource(R.string.delete), fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ✔️ Sjekk svar-knapp
                Button(
                    onClick = { gameViewModel.checkAnswer() },
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.accentYellow))
                ) {
                    Text(stringResource(R.string.check_answer), fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(feedback, fontSize = 22.sp)

            if (answered) {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { gameViewModel.nextQuestion() },
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.accentOrange))
                ) {
                    Text(stringResource(R.string.next_task), fontSize = 20.sp)
                }
            }
        }
    }

    // 🔹 Avslutt dialog
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { gameViewModel.dismissExitDialog() },
            title = { Text(stringResource(R.string.exit_game)) },
            text = { Text(stringResource(R.string.exit_game_confirm)) },
            confirmButton = {
                Button(
                    onClick = { gameViewModel.dismissExitDialog(); onBack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.accentOrange)
                    )
                ) {
                    Text(stringResource(R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = { gameViewModel.dismissExitDialog() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.accentRed)
                    )
                ) {
                    Text(stringResource(R.string.no))
                }
            }
        )
    }
}
