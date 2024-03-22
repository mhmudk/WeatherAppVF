package com.example.weatherappvf.presentation.features

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable



@Composable
fun ShowDialogError(error: String) {
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = "Error") },
        text = { Text(text = error) },
        confirmButton = {
            Button(
                onClick = {
                }
            ) {
                Text("OK")
            }
        }
    )
}