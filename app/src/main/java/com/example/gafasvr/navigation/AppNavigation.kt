package com.example.gafasvr.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gafasvr.screens.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("timer") { TimerScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("youtube") { YouTubeScreen(navController) }
        composable("facebook") { FacebookScreen(navController) }
        composable("gallery") { GalleryScreen(navController) }
        composable("memory_game") { MemoryGameScreen(navController) }
        composable("study_mode") { StudyModeScreen(navController) }


    }
}
