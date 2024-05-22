package com.example.lingo.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingo.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher
):ViewModel(){
    fun getCourses(){
        viewModelScope.launch(ioDispatcher)
        {repository.getCourseNames()}
    }

    fun getUsername(id: Int){
        viewModelScope.launch(ioDispatcher){
            repository.getUsername(id)
        }
    }
}