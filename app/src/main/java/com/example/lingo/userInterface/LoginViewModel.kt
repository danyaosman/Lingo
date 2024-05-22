package com.example.lingo.userInterface
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class LoginViewModel : ViewModel() {
    val username = mutableStateOf("")
    val password = mutableStateOf("")
}
