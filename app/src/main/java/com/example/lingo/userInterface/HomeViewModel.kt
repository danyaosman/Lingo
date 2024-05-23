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

    fun getCourses(firstTime: Boolean):List<Course>{
        if(firstTime)initCourseList(repository)
        var courses = listOf<Course>()
        viewModelScope.launch(ioDispatcher)
        {courses = repository.getCourses()}
        return courses
    }

}