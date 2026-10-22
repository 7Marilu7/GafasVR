package com.example.gafasvr.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun StudyModeScreen(navController: NavController) {
    val context = LocalContext.current

    var studyTimeLeft by remember { mutableStateOf(25 * 60) } // 25 minutos
    var isRunning by remember { mutableStateOf(false) }
    var isOnBreak by remember { mutableStateOf(false) }
    var breakTimeLeft by remember { mutableStateOf(5 * 60) }

    LaunchedEffect(isRunning, isOnBreak) {
        while (isRunning) {
            delay(1000)
            if (isOnBreak) {
                if (breakTimeLeft > 0) {
                    breakTimeLeft -= 1
                } else {
                    isOnBreak = false
                    isRunning = true // reanuda el estudio
                    Toast
                        .makeText(context, "¡Descanso terminado! Vuelve al estudio 💪", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                if (studyTimeLeft > 0) {
                    studyTimeLeft -= 1
                } else {
                    isRunning = false
                }
            }
        }
    }

    val minutes = if (isOnBreak) breakTimeLeft / 60 else studyTimeLeft / 60
    val seconds = if (isOnBreak) breakTimeLeft % 60 else studyTimeLeft % 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            if (isOnBreak) "Modo Descanso" else "Modo Estudio",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            String.format("%02d:%02d", minutes, seconds),
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { isRunning = true }) {
                Text("Iniciar")
            }
            Button(onClick = { isRunning = false }) {
                Text("Pausar")
            }
            Button(onClick = {
                isRunning = false
                isOnBreak = false
                studyTimeLeft = 25 * 60
                breakTimeLeft = 5 * 60
            }) {
                Text("Reiniciar")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (!isOnBreak && studyTimeLeft > 0) {
                    isOnBreak = true
                    breakTimeLeft = 5 * 60
                    isRunning = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF80AB)) // rosita
        ) {
            Text("Descanso")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Regresar")
        }
    }
}
