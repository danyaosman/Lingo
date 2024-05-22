package com.example.lingo.repository

import com.example.lingo.room.Course
import com.example.lingo.room.CourseDao
import com.example.lingo.room.Question
import com.example.lingo.room.QuestionDao
import com.example.lingo.room.User
import com.example.lingo.room.UsersDao

class Repository (
    private val usersDao:UsersDao,
    private val questionDao: QuestionDao,
    private val courseDao: CourseDao
){
    fun getUserById(id:Int) = usersDao.getUserById(id)
    fun getUserByName(name:String) = usersDao.getUserByName(name)
    fun getQuestionsByCourse(courseId: Int) = questionDao.getQuestionsByCourse(courseId)
    fun getCourseNames() = courseDao.getCourseNames()

    suspend fun insertUser(user: User) {
        usersDao.insert(user)
    }

    suspend fun insertQuestion(question:Question){
        questionDao.insert(question)
    }
}