package com.example.gafasvr.screens

import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TimerScreen(navController: NavController) {
    var timeLeft by remember { mutableStateOf(60) }
    var running by remember { mutableStateOf(false) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    LaunchedEffect(running) {
        if (running) {
            timer = object : CountDownTimer(timeLeft * 1000L, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeLeft = (millisUntilFinished / 1000).toInt()
                }

                override fun onFinish() {
                    running = false
                }
            }.start()
        } else {
            timer?.cancel()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tiempo restante", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("$timeLeft s", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { running = !running },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (running) "Detener" else "Iniciar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regresar")
        }
    }
}

