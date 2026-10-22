package com.example.gafasvr.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gafasvr.R
import androidx.compose.foundation.background

@Composable
fun YouTubeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))  // Fondo blanco/grisáceo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sección principal
            Text("YouTube", style = MaterialTheme.typography.titleLarge, color = Color.Black)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Video destacado
                Card(
                    modifier = Modifier
                        .weight(1.5f)
                        .height(180.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.video_thumbnail3),
                        contentDescription = "Frida Kahlo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Lista de videos relacionados
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.video_thumbnail),
                                contentDescription = "Video Relacionado",
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    "Pinturas más famosas de...",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black
                                )
                                Text(
                                    "Vista previa",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

                // Anuncio
                Card(
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("Publicidad", color = Color.Black)
                    }
                }
            }

            // Sección de más videos
            Text("Más videos", style = MaterialTheme.typography.titleMedium, color = Color.Black)

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(1) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        repeat(3) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.video_thumbnail2),
                                    contentDescription = "Video",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                )
                                Text(
                                    "Viva la vida",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
