package com.example.gafasvr.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState


@Composable
fun SettingsScreen(navController: NavController) {
    // Estados
    var darkModeEnabled by remember { mutableStateOf(false) }
    var soundEnabled by remember { mutableStateOf(true) }
    var immersiveMode by remember { mutableStateOf(false) }
    var textSize by remember { mutableStateOf(16f) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Barra superior: Perfil + WiFi
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Perfil",
                    modifier = Modifier.size(32.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Marilu", fontSize = 18.sp, color = Color.White)
            }

            Icon(
                imageVector = Icons.Default.Wifi,
                contentDescription = "WiFi",
                modifier = Modifier.size(28.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Ajustes", style = MaterialTheme.typography.titleLarge, color = Color.White)
        Text("Configura según tus preferencias", color = Color.LightGray)

        // Modo oscuro
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Modo oscuro", modifier = Modifier.weight(1f), color = Color.White)
            Switch(
                checked = darkModeEnabled,
                onCheckedChange = { darkModeEnabled = it }
            )
        }

        // Sonido
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sonido activado", modifier = Modifier.weight(1f), color = Color.White)
            Switch(
                checked = soundEnabled,
                onCheckedChange = { soundEnabled = it }
            )
        }

        // Modo inmersivo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Modo inmersivo", modifier = Modifier.weight(1f), color = Color.White)
            Switch(
                checked = immersiveMode,
                onCheckedChange = { immersiveMode = it }
            )
        }

        // Tamaño de texto
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Tamaño del texto: ${textSize.toInt()}", color = Color.White)
            Slider(
                value = textSize,
                onValueChange = { textSize = it },
                valueRange = 12f..24f
            )
        }

        // Botón reset
        Button(
            onClick = {
                darkModeEnabled = false
                soundEnabled = true
                immersiveMode = false
                textSize = 16f
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Restablecer ajustes", color = Color.White)
        }

        // Botón regresar
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regresar")
        }
    }
}
