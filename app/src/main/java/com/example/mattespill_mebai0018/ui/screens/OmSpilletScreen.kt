package com.example.mattespill_mebai0018.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mattespill_mebai0018.R
import com.example.mattespill_mebai0018.ui.components.BackButton

@Composable
fun OmSpilletScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 80.dp) // 👈 dytt ned hele innholdet
        ) {
            Text(
                stringResource(R.string.about_title),
                fontSize = 34.sp,
                color = colorResource(id = R.color.accentOrange)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                stringResource(R.string.about_text),
                fontSize = 22.sp,
                color = colorResource(id = R.color.owlBrown)
            )

            Spacer(modifier = Modifier.height(40.dp))

            BackButton(onClick = onBack)
        }

        // 🦉 Stor ugle nederst
        Image(
            painter = painterResource(id = R.drawable.owl),
            contentDescription = "Uglen",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

