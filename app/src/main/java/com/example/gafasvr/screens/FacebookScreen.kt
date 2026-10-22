package com.example.gafasvr.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gafasvr.R

@Composable
fun FacebookScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)) // fondo gris claro
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Facebook",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        repeat(2) { index ->
            PostCard(
                profileImage = R.drawable.profile_imagen,
                username = "Marilu $index",
                postImage = R.drawable.post_example,
                description = "Hi, sky  #$index ."
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PostCard(
    profileImage: Int,
    username: String,
    postImage: Int,
    description: String
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = profileImage),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )
                Text(username, fontSize = 16.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(description, fontSize = 14.sp, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = postImage),
                contentDescription = "Imagen del post",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Me gusta",
                    tint = Color.Red
                )
                Icon(
                    imageVector = Icons.Default.ChatBubble,
                    contentDescription = "Comentar",
                    tint = Color.Gray
                )
            }
        }
    }
}

