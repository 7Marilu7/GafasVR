package com.example.gafasvr.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gafasvr.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import android.widget.Toast

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    // Estado para la hora
    var currentTime by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        while(true) {
            currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
            kotlinx.coroutines.delay(60000) // actualizar cada 60 seg
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Ojo izquierdo
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fondo_vr),
                    contentDescription = "Fondo VR Ojo Izquierdo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp, end = 40.dp)
                ) {
                    // Primer ícono centrado
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_youtube),
                            contentDescription = "YouTube",
                            modifier = Modifier
                                .size(64.dp)
                                .clickable { navController.navigate("youtube") }
                        )
                        Text(
                            "YouTube",
                            fontSize = 12.sp,
                            color = androidx.compose.ui.graphics.Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Dos íconos en fila
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_facebook),
                                contentDescription = "Facebook",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clickable { navController.navigate("facebook") }
                            )
                            Text(
                                "Facebook",
                                fontSize = 12.sp,
                                color = androidx.compose.ui.graphics.Color.Black
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_gallery),
                                contentDescription = "Galería",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clickable { navController.navigate("gallery") }
                            )
                            Text(
                                "Galería",
                                fontSize = 12.sp,
                                color = androidx.compose.ui.graphics.Color.Black
                            )
                        }
                    }
                }
            }

            // Ojo derecho
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fondo_vr),
                    contentDescription = "Fondo VR Ojo Derecho",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp, end = 40.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_game),
                            contentDescription = "Memorama",
                            modifier = Modifier
                                .size(64.dp)
                                .clickable { navController.navigate("memory_game") }
                        )
                        Text(
                            "Memorama",
                            fontSize = 12.sp,
                            color = androidx.compose.ui.graphics.Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_timer),
                                contentDescription = "Temporizador",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clickable { navController.navigate("timer") }
                            )
                            Text(
                                "Temporizador",
                                fontSize = 12.sp,
                                color = androidx.compose.ui.graphics.Color.Black
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_settings),
                                contentDescription = "Ajustes",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clickable { navController.navigate("settings") }
                            )
                            Text(
                                "Ajustes",
                                fontSize = 12.sp,
                                color = androidx.compose.ui.graphics.Color.Black
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_study), // asegúrate de tener este drawable
                                contentDescription = "Modo Estudio",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clickable { navController.navigate("study_mode") }
                            )
                            Text(
                                "Estudio",
                                fontSize = 12.sp,
                                color = androidx.compose.ui.graphics.Color.Black
                            )
                        }
                    }
                }
            }
        }

        // HUD superior: hora y batería
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(currentTime, color = androidx.compose.ui.graphics.Color.Black, fontSize = 16.sp)
            Icon(
                imageVector = Icons.Default.BatteryFull,
                contentDescription = "Batería",
                tint = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        // Botón micrófono para activar voz
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Activar Voz",
                tint = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier
                    .size(56.dp)
                    .clickable {
                        Toast.makeText(context, "Reconocimiento de voz activado", Toast.LENGTH_SHORT).show()
                    }
            )
        }
    }
}