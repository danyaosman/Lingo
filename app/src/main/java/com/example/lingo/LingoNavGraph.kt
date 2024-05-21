package com.example.lingo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.lingo.room.Question
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

val questionData = Question(
    question = "What is your question?",
    options = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
    answer = "Option 1",
    id = 1
)
@Composable
fun LingoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel()
) {
    var startDes by remember { mutableStateOf("Login") }

    LaunchedEffect(loginViewModel.username.value) {
        startDes = if (loginViewModel.username.value.isEmpty()) "Login" else "Home"
    }

    NavHost(navController = navController, startDestination = startDes, modifier = modifier) {
        composable(route = "Login") {
            LoginScreen(navController, loginViewModel)
        }
        composable(route = "Home") {
            HomeScreen(loginViewModel,navController)
        }
        composable(route = "Levels/{courseId}") {
            LevelsScreen(navController)
        }
        composable(route = "Questions/{levelId}") {
            QuestionScreen(questionData, 1)
        }
    }
}

