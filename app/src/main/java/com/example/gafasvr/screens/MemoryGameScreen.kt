package com.example.gafasvr.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

data class Card(
    val id: Int,
    val content: String,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)

@Composable
fun MemoryGameScreen(navController: NavController) {
    val emojis = listOf("🍎", "🍌", "🍇")
    var cards by remember { mutableStateOf(generateCards(emojis)) }
    var firstSelectedIndex by remember { mutableStateOf<Int?>(null) }
    var secondSelectedIndex by remember { mutableStateOf<Int?>(null) }
    var lockClick by remember { mutableStateOf(false) }

    // Este efecto espera para voltear las cartas si no son iguales
    LaunchedEffect(key1 = secondSelectedIndex) {
        if (secondSelectedIndex != null) {
            val firstCard = cards[firstSelectedIndex!!]
            val secondCard = cards[secondSelectedIndex!!]
            if (firstCard.content != secondCard.content) {
                lockClick = true
                delay(1000)
                cards = cards.toMutableList().also {
                    it[firstSelectedIndex!!] = firstCard.copy(isFaceUp = false)
                    it[secondSelectedIndex!!] = secondCard.copy(isFaceUp = false)
                }
            } else {
                cards = cards.toMutableList().also {
                    it[firstSelectedIndex!!] = firstCard.copy(isMatched = true)
                    it[secondSelectedIndex!!] = secondCard.copy(isMatched = true)
                }
            }
            firstSelectedIndex = null
            secondSelectedIndex = null
            lockClick = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Memorama para el Chalino", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        for (row in 0..1) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                for (col in 0..2) {
                    val index = row * 3 + col
                    val card = cards[index]
                    CardView(card) {
                        if (card.isFaceUp || card.isMatched || lockClick) return@CardView

                        cards = cards.toMutableList().also {
                            it[index] = card.copy(isFaceUp = true)
                        }

                        if (firstSelectedIndex == null) {
                            firstSelectedIndex = index
                        } else if (secondSelectedIndex == null) {
                            secondSelectedIndex = index
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            cards = generateCards(emojis)
            firstSelectedIndex = null
            secondSelectedIndex = null
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Reiniciar Juego")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Regresar")
        }
    }
}

fun generateCards(emojis: List<String>): List<Card> {
    return (emojis + emojis).shuffled().mapIndexed { index, emoji ->
        Card(id = index, content = emoji)
    }
}

@Composable
fun CardView(card: Card, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(
                color = when {
                    card.isMatched -> Color.Green.copy(alpha = 0.6f)
                    card.isFaceUp -> Color.White
                    else -> Color.DarkGray
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (card.isFaceUp || card.isMatched) {
            Text(text = card.content, fontSize = 32.sp)
        }
    }
}