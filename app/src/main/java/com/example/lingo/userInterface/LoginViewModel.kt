package com.example.lingo.userInterface
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.lingo.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingo.room.User
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher
):ViewModel(){

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()
    fun setUsername(name: String) {
        _username.value = name
    }
    fun setPassword(pass: String) {
        _password.value = pass
    }
    fun insertUser(user: User) {
        viewModelScope.launch(ioDispatcher) {
            repository.insertUser(user)
        }
    }

    fun clearUser() {
        _user.value = null
        _username.value = ""
        _password.value = ""
    }
    fun setUser(user: User) {
        _user.value = user
        _username.value = user.username
        _password.value = user.password
    }
    suspend fun getUser(username: String): User? {
        return viewModelScope.async(ioDispatcher) {
            repository.getUserByName(username)
        }.await()
    }
}