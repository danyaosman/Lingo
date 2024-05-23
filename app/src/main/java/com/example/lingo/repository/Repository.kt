package com.example.lingo.repository

import com.example.lingo.room.Course
import com.example.lingo.room.CourseDao

import com.example.lingo.room.Question
import com.example.lingo.room.QuestionDao
import com.example.lingo.room.User
import com.example.lingo.room.UserCourses
import com.example.lingo.room.UserCoursesDao
import com.example.lingo.room.UsersDao

class Repository (
    private val usersDao:UsersDao,
    private val questionDao: QuestionDao,
    private val courseDao: CourseDao,
    private val userCoursesDao: UserCoursesDao,
    ){
    fun getUserById(id:Int) = usersDao.getUserById(id)
    fun getUserByName(name:String) = usersDao.getUserByName(name)
    fun getQuestionsByCourse(courseId: Int) = questionDao.getQuestionsByCourse(courseId)
    suspend fun getCourses() = courseDao.getCourses()

    fun getUserCourses() = userCoursesDao.getUserCourses()

    suspend fun insertUser(user: User) {
        usersDao.insert(user)
    }

    suspend fun insertQuestion(question:Question){
        questionDao.insert(question)
    }

    suspend fun insertCourse(course: Course) {
        courseDao.insert(course)
    }

    suspend fun insertUserCourse(userCourse: UserCourses) {
        userCoursesDao.insert(userCourse)
    }
}