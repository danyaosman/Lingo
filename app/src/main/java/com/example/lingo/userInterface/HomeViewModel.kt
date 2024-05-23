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
    private val _course= MutableStateFlow<Course?>(null)
    val course: StateFlow<Course?> = _course.asStateFlow()
    private val _courseId = MutableStateFlow(0)
    val courseId: StateFlow<Int> = _courseId.asStateFlow()
    init {
        viewModelScope.launch {
            var coursesRefreshed = false // Flag to track whether courses have been refreshed

            val refreshJob = async {
                refreshCourses() // Launch refreshCourses() asynchronously
                coursesRefreshed = true // Set flag to true after refreshCourses() completes
            }

            // Wait for refreshCourses() to complete
            refreshJob.await()

            // Once refreshCourses() is completed, collect courses
            courses.collect { courseList ->
                if (coursesRefreshed && courseList.isEmpty()) {
                    // Courses list is empty and refreshCourses() has been executed
                    initCourseList(repository)
                }
            }
        }
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
    fun setSelectedCourse(course: Course) {
        _course.value = course
    }
    fun setSelectedCourseId(course: Int) {
        _courseId.value = course
    }
}