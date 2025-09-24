package com.example.mattespill_mebai0018.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mattespill_mebai0018.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


// 🔢 Tallknapp
@Composable
fun RowScope.AnswerButton(digit: String, onClick: (String) -> Unit) {
    val buttonColor = when (digit) {
        "1", "4", "7" -> colorResource(id = R.color.primaryBlue)
        "2", "5", "8" -> colorResource(id = R.color.accentYellow)
        "3", "6", "9", "0" -> colorResource(id = R.color.accentOrange)
        else -> colorResource(id = R.color.primaryBlue)
    }

    Button(
        onClick = { onClick(digit) },
        modifier = Modifier
            .padding(6.dp)
            .weight(1f)
            .height(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.Black // svart talltekst → tydelig kontrast
        )
    ) {
        Text(digit, fontSize = 28.sp)
    }
}





// 🌟 Generisk knapp til menyen

@Composable
fun AppButton(
    text: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Icon(icon, contentDescription = text)
        Spacer(Modifier.width(8.dp))
        Text(text, fontSize = 20.sp)
    }
}



// 🔢 Tall-knapp-grid
@Composable
fun AnswerPad(onDigitClick: (String) -> Unit) {
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
                    AnswerButton(digit = digit, onClick = onDigitClick)
                }
            }
        }
    }
}

// ✔️ Sjekk svar
@Composable
fun CheckAnswerButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.green), // 👈 Nå fungerer dette
            contentColor = Color.White
        )
    ) {
        Text(stringResource(R.string.check_answer), fontSize = 20.sp)
    }
}



// ❌ Slett knapp
@Composable
fun DeleteButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.accentRed)
        )
    ) {
        Text(stringResource(R.string.delete), fontSize = 20.sp)
    }
}


// ❌ Tilbake-knapp
@Composable
fun BackButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
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


//Velg knapp
@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    selectedColor: Color = colorResource(id = R.color.primaryBlue),
    defaultColor: Color = colorResource(id = R.color.accentYellow)
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) selectedColor else defaultColor,
            contentColor = Color.Black
        )
    ) {
        Text(text)
    }
}



// ⏭️ Neste oppgave
@Composable
fun NextTaskButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.accentOrange)
        )
    ) {
        Text(stringResource(R.string.next_task), fontSize = 20.sp)
    }
}
