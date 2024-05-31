package com.example.lingo.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingo.repository.Repository
import com.example.lingo.room.Course
import initCourseList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher
):ViewModel() {
    val courses = MutableStateFlow<List<Course>>(emptyList())
    val course = MutableStateFlow<Course?>(null)

    init {
        viewModelScope.launch {
            var coursesRefreshed = false

            val refreshJob = async {
                getCourses()
                coursesRefreshed = true
            }
            refreshJob.await()

            courses.collect { courseList ->
                if (coursesRefreshed && courseList.isEmpty()) {
                    initCourseList(repository)
                }
            }
        }
    }

    fun getCourses() {
        viewModelScope.launch(ioDispatcher) {
            courses.value = repository.getCourses()
        }
    }

    fun setSelectedCourse(course: Course) {
        this.course.value = course
    }
}
