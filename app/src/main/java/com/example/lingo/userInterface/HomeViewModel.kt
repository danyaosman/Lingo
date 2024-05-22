package com.example.lingo.userInterface

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingo.Graph
import com.example.lingo.repository.Repository
import com.example.lingo.room.Course
import com.example.lingo.room.User
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

}