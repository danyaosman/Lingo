package com.example.lingo.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingo.repository.Repository
import com.example.lingo.room.Course
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()

    private val _course= MutableStateFlow<Course?>(null)
    val course: StateFlow<Course?> = _course.asStateFlow()

    init {
        viewModelScope.launch {
            refreshCourses()
        }
    }

    private suspend fun refreshCourses() {
        _courses.value = repository.getCourses()
    }

    fun setSelectedCourse(course: Course) {
        _course.value = course
    }
}
