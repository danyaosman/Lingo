package com.example.lingo.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingo.repository.Repository
import com.example.lingo.room.Course
import com.example.lingo.room.Question
import com.example.lingo.room.User
import initQuestions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class QuestionsViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()


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
            questions.collect { courseList ->
                if (coursesRefreshed && courseList.isEmpty()) {
                    // Courses list is empty and refreshCourses() has been executed
                    initQuestions(repository)
                }
            }
        }
    }

    private fun refreshCourses() {
        viewModelScope.launch(ioDispatcher) {
            _questions.value = repository.getQuestions()
        }
    }

    fun getQuestions() {
        viewModelScope.launch(ioDispatcher) {
            _questions.value = repository.getQuestions()
        }
    }

}
