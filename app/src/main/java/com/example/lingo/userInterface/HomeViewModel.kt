package com.example.lingo.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingo.repository.Repository
import com.example.lingo.room.Course
import com.example.lingo.room.User
import initCourseList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher
):ViewModel(){


    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()

    init {
        refreshCourses() // Load courses when ViewModel is created
        initCourseList(repository)
    }

    private fun refreshCourses() {
        viewModelScope.launch(ioDispatcher) {
            _courses.value = repository.getCourses()
        }
    }

    fun getCourses() {
        viewModelScope.launch(ioDispatcher) {
            _courses.value = repository.getCourses()
        }
    }

}