package com.example.lingo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost

@Composable
fun LingoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController,
        startDestination = "Login",
        modifier = modifier){
        composable(route="Login"){
            LoginScreen()
        }
        composable(route = "Home") {
            HomeScreen()
        }
        composable (route = "Levels"){
            LevelsScreen()
        }
        composable (route = "Questions") {
            QuestionsScreen()
        }
    }
}