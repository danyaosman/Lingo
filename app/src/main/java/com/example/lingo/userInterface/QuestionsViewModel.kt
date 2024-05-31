package com.example.lingo.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingo.repository.Repository
import com.example.lingo.room.Course
import com.example.lingo.room.Question
import initQuestions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class QuestionsViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _allQuestions = MutableStateFlow<List<Question>>(emptyList())
    private val _courseQuestions = MutableStateFlow<List<Question>>(emptyList())
    val allQuestions: StateFlow<List<Question>> = _allQuestions.asStateFlow()
    val courseQuestions: StateFlow<List<Question>> = _courseQuestions.asStateFlow()
    private val _course= MutableStateFlow<Course?>(null)
    val course: StateFlow<Course?> = _course.asStateFlow()
    private val _courseId = MutableStateFlow(0)
    val courseId: StateFlow<Int> = _courseId.asStateFlow()
    init {
        viewModelScope.launch {
            var coursesRefreshed = false // Flag to track whether courses have been refreshed
            val refreshJob = async {
                refreshAllQuestions() // Launch refreshCourses() asynchronously
                coursesRefreshed = true // Set flag to true after refreshCourses() completes
            }

            // Wait for refreshCourses() to complete
            refreshJob.await()

            // Once refreshCourses() is completed, collect courses
            allQuestions.collect { allQuestionsList ->
                if (coursesRefreshed && allQuestionsList.isEmpty()) {
                    // Courses list is empty and refreshCourses() has been executed
                    initQuestions(repository)
                }
            }
        }
    }

    private fun refreshAllQuestions() {
        viewModelScope.launch(ioDispatcher) {
            _allQuestions.value = repository.getQuestions()
        }
    }

    fun getQuestions() {
        viewModelScope.launch(ioDispatcher) {
            _allQuestions.value = repository.getQuestions()
        }
    }

    fun getQuestionsByCourseID(courseId: Int) {
        viewModelScope.launch(ioDispatcher) {
            val newQuestions = repository.getQuestionsByCourse(courseId)
            _courseQuestions.value = newQuestions
        }
    }


}
