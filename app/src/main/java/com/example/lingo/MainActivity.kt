package com.example.lingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.lingo.repository.Repository
import com.example.lingo.room.LingoDatabase
import com.example.lingo.ui.theme.LingoTheme
import com.example.lingo.userInterface.HomeViewModel
import com.example.lingo.userInterface.LoginViewModel
import com.example.lingo.userInterface.QuestionsViewModel
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //no singleton design pattern (yet)
        val db = LingoDatabase.getDatabase(context = this)
        // 2. Manual MainViewModel Creation
        val repo = Repository(db.usersDao(), db.questionDao(), db.courseDao(), db.userCoursesDao())
        val loginViewModel = LoginViewModel(
            repo,
            ioDispatcher = Dispatchers.IO)
        val homeViewModel = HomeViewModel(
            repo,
            ioDispatcher = Dispatchers.IO)
        val questionViewModel = QuestionsViewModel(
            repo,
            ioDispatcher = Dispatchers.IO)
        setContent {
            LingoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LingoNavHost(questionViewModel=questionViewModel,loginViewModel = loginViewModel, homeViewModel=homeViewModel, navController = rememberNavController())
                }
            }
        }
    }
}
