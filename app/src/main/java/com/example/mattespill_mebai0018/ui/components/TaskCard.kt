package com.example.mattespill_mebai0018.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mattespill_mebai0018.R

@Composable
fun TaskCard(index: Int, task: String, userInput: String) {
    Text(
        text = "${stringResource(R.string.task_title)} ${index + 1}",
        fontSize = 22.sp
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = task,
        fontSize = 36.sp,
        color = colorResource(id = R.color.primaryBlue)
    )
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = "${stringResource(R.string.your_answer)} $userInput",
        fontSize = 22.sp
    )
}
