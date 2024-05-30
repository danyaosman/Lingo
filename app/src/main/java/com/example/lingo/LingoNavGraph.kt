package com.example.lingo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.lingo.room.Question
import androidx.compose.runtime.*
import com.example.lingo.userInterface.HomeScreen
import com.example.lingo.userInterface.HomeViewModel
import com.example.lingo.userInterface.LevelsScreen
import com.example.lingo.userInterface.LoginScreen
import com.example.lingo.userInterface.LoginViewModel
import com.example.lingo.userInterface.QuestionScreen
import com.example.lingo.userInterface.QuestionsViewModel

@Composable
fun LingoNavHost(
    loginViewModel: LoginViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    questionViewModel:QuestionsViewModel= viewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val courseIndexState = remember { mutableStateOf(0) }

    var startDes by remember { mutableStateOf("Login") }

    LaunchedEffect(loginViewModel.username.value) {
        startDes = if (loginViewModel.username.value.isEmpty()) "Login" else "Home"
    }

    NavHost(navController = navController, startDestination = startDes, modifier = modifier) {
        composable(route = "Login") {
            LoginScreen(loginViewModel,navController)
        }
        composable(route = "Home") {
            HomeScreen(homeViewModel,loginViewModel,navController)
        }
        composable(route = "Questions/{courseId}") {
            QuestionScreen(questionViewModel, homeViewModel,navController )
        }
    }
}

