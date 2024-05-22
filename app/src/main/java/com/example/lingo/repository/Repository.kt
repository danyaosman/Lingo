package com.example.lingo.repository

import com.example.lingo.room.Course
import com.example.lingo.room.LevelDao
import com.example.lingo.room.QuestionList
import com.example.lingo.room.QuestionListDao
import com.example.lingo.room.User
import com.example.lingo.room.UsersDao

class Repository (
    private val usersDao:UsersDao,
    private val questionsListDao: QuestionListDao,
    private val levelDao: LevelDao
){
    fun user(username:String)=usersDao.getUser(username)
    fun questions(levelId: Int)=questionsListDao.getQuestionlistByLvl(levelId)
    fun levels(courseId: Int)=levelDao.getCourseLevels(courseId)

    suspend fun insertUser(user: User) {
        usersDao.insert(user)
    }
    suspend fun insertQuestion(questionList:QuestionList){
        questionsListDao.insert(questionList)
    }

}