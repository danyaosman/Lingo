package com.example.lingo.userInterface
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.lingo.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher

class LoginViewModel(
                     private val repository: Repository,
                     private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val username = mutableStateOf("")
    val password = mutableStateOf("")
}
