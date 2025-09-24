package com.example.mattespill_mebai0018.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.colorResource
import com.example.mattespill_mebai0018.R

@Composable
fun ExitConfirmationDialog(
    show: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(stringResource(R.string.exit_game)) },
            text = { Text(stringResource(R.string.exit_game_confirm)) },
            confirmButton = {
                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.accentOrange)
                    )
                ) {
                    Text(stringResource(R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss,
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
