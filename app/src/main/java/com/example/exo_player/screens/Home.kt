package com.example.exo_player.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Home(
    onOnlineClick: () -> Unit,
    onRawClick: () -> Unit,
    onYoutubeClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Online Player :->
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            enabled = true,
            onClick = onOnlineClick
        ) {
            Text(text = "Online Player")
        }

        Spacer(modifier = Modifier.height(7.dp))

        // Raw Player :->
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            enabled = true,
            onClick = onRawClick
        ) {
            Text(text = "Raw Player")
        }

        Spacer(modifier = Modifier.height(7.dp))

        // Youtube Player :->
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            enabled = true,
            onClick = onYoutubeClick
        ) {
            Text(text = "Youtube Player")
        }
    }
}