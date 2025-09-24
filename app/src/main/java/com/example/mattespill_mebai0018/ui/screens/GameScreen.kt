package com.example.mattespill_mebai0018.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mattespill_mebai0018.R
import com.example.mattespill_mebai0018.ui.ViewModels.GameViewModel
import com.example.mattespill_mebai0018.ui.components.*

@Composable
fun GameScreen(onBack: () -> Unit) {
    val gameViewModel: GameViewModel = viewModel()

    val userInput by gameViewModel.userInput
    val feedback by gameViewModel.feedback
    val score by gameViewModel.score
    val finished by gameViewModel.finished
    val answered by gameViewModel.answered
    val showExitDialog by gameViewModel.showExitDialog
    val newHighscore by gameViewModel.newHighscore
    val errorMessage by gameViewModel.errorMessage
    val currentIndex by gameViewModel.currentIndex
    val totalQuestions = gameViewModel.totalQuestions




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // 🔙 Tilbake-knapp øverst til venstre (kun hvis spillet ikke er ferdig)
        if (!finished) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                BackButton(onClick = { gameViewModel.showExitDialog() })
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                fontSize = 20.sp,
                color = colorResource(id = R.color.accentRed)
            )
        } else if (finished) {
            // 🏁 Sluttskjerm
            Text(stringResource(R.string.game_over), fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("${stringResource(R.string.score)}: $score", fontSize = 24.sp)

            if (newHighscore) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(R.string.new_highscore),
                    fontSize = 28.sp,
                    color = colorResource(id = R.color.green)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { onBack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green)
                )
            ) {
                Text(stringResource(R.string.play_again), fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            // 🦉 Stor ugle nederst på sluttskjermen
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.owl),
                    contentDescription = "Uglen",
                    modifier = Modifier.size(320.dp)
                )
            }


        } else {
            val (task, _) = gameViewModel.currentTask()

            // 🔹 Oppgaveteller
            Text(
                text = "${currentIndex + 1} / $totalQuestions",
                fontSize = 20.sp,
                color = colorResource(id = R.color.owlBrown),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Oppgave
            Text(
                task,
                fontSize = 72.sp,
                color = colorResource(id = R.color.primaryBlue),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 🔹 Brukerens svar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .border(BorderStroke(2.dp, Color.Black)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userInput,
                    fontSize = 48.sp,
                    color = colorResource(id = R.color.accentOrange)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (!answered) {
                Column {
                    for (row in listOf(
                        listOf("1", "2", "3"),
                        listOf("4", "5", "6"),
                        listOf("7", "8", "9")
                    )) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row.forEach { digit ->
                                AnswerButton(digit = digit, onClick = { gameViewModel.addDigit(it) })
                            }
                        }
                    }
                    // 0-knappen
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        AnswerButton(digit = "0", onClick = { gameViewModel.addDigit("0") })
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                DeleteButton(onClick = { gameViewModel.deleteLastDigit() })
                Spacer(modifier = Modifier.height(20.dp))
                CheckAnswerButton(onClick = { gameViewModel.checkAnswer() })
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(feedback, fontSize = 24.sp)

            if (answered) {
                Spacer(modifier = Modifier.height(24.dp))
                NextTaskButton(onClick = { gameViewModel.nextQuestion() })
            }

            Spacer(modifier = Modifier.weight(1f))

            // 🦉 Liten ugle nederst mens man spiller
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.owl),
                    contentDescription = "Uglen",
                    modifier = Modifier.size(200.dp)
                )
            }

        }
    }

    // 🔹 Exit-dialog
    ExitConfirmationDialog(
        show = showExitDialog,
        onConfirm = { gameViewModel.dismissExitDialog(); onBack() },
        onDismiss = { gameViewModel.dismissExitDialog() }
    )
}
