package com.example.mattespill_mebai0018.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mattespill_mebai0018.R

@Composable
fun OmSpilletScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.about_title),
            fontSize = 28.sp,
            color = colorResource(id = R.color.accentOrange)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.about_text),
            fontSize = 18.sp,
            color = colorResource(id = R.color.owlBrown)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.primaryBlue)
            )
        ) {
            Text(stringResource(R.string.back))
        }
    }
}
