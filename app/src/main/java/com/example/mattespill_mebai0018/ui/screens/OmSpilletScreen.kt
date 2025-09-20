package com.example.mattespill_mebai0018.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OmSpilletScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Om spillet", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Dette er et enkelt mattespill laget for barn.\n\n" +
                    "Du får oppgaver med addisjon, og du svarer ved å trykke på tallene.\n\n" +
                    "Spillet gir deg tilbakemelding om du har svart riktig eller feil, " +
                    "og holder telling på poengene dine.",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onBack) {
            Text("Tilbake")
        }
    }
}
