package com.example.lingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.lingo.room.LingoDatabase
import com.example.lingo.ui.theme.LingoTheme
import com.example.lingo.userInterface.LoginViewModel
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //no singleton design pattern (yet)
        val db = LingoDatabase.getDatabase(context = this)
        // 2. Manual MainViewModel Creation
        val loginViewModel = LoginViewModel(OfflinePeopleRepository(db.personDao()),
            ioDispatcher = Dispatchers.IO)

        setContent {
            RoomFriAfternoonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartApp(mainViewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()

    LingoNavHost(navController)
}