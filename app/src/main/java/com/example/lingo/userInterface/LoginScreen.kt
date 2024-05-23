package com.example.lingo.userInterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lingo.R
import com.example.lingo.room.User
import com.example.lingo.ui.theme.Brown
import com.example.lingo.ui.theme.Green
import com.example.lingo.ui.theme.Orange
import com.example.lingo.ui.theme.Yellow
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.layout.Column
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(homeViewModel: HomeViewModel,loginViewModel: LoginViewModel, navController: NavHostController) {

    val username by loginViewModel.username.collectAsStateWithLifecycle()
    val password by loginViewModel.password.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    val onNameEntered: (String) -> Unit = remember {
        { name ->
            loginViewModel.setUsername(name)
        }
    }

    val onPasswordEntered: (String) -> Unit = remember {
        { pass ->
            loginViewModel.setPassword(pass)
        }
    }

    val onSubmit: () -> Unit = {
        coroutineScope.launch {
            val currentUser = User( username = username, password = password)
            val retrievedUser = loginViewModel.getUser(currentUser.username)
            if (retrievedUser?.username != ""&&retrievedUser!=null) {
                loginViewModel.setUser(retrievedUser)
            } else {
                // Insert new user
                loginViewModel.insertUser(currentUser)
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(120.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = username,
            onValueChange = onNameEntered,
            label = { Text("Username") },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Yellow,
                focusedIndicatorColor = Color.Yellow,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.Yellow,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = onPasswordEntered,
            label = { Text("Password") },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),

            colors = TextFieldDefaults.textFieldColors(
                containerColor = Yellow,
                focusedIndicatorColor = Color.Yellow,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.Yellow,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                onSubmit()
                navController.navigate("Home")
            },
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .padding(vertical = 7.dp, horizontal = 17.dp)
                    .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor = Brown
            ))
        {
            Text(text = "Login")
        }
    }
}

