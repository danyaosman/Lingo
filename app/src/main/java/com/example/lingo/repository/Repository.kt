package com.example.lingo.repository

import com.example.lingo.room.Course
import com.example.lingo.room.LevelDao
import com.example.lingo.room.Question
import com.example.lingo.room.QuestionDao
import com.example.lingo.room.User
import com.example.lingo.room.UsersDao

class Repository (
    private val usersDao:UsersDao,
    private val questionDao: QuestionDao,
    private val levelDao: LevelDao
){
    fun getUserById(id:Int) = usersDao.getUserById(id)
    fun getUserByName(name:String) = usersDao.getUserByName(name)
    fun getQuestionListByLvl(lvlId:Int,courseId: Int) = questionDao.getQuestionsByLvlAndCourse(lvlId, courseId)

    fun getCourseLevels(courseId: Int)=levelDao.getCourseLevels(courseId)

    suspend fun insertUser(user: User) {
        usersDao.insert(user)
    }
    suspend fun insertQuestion(question:Question){
        questionDao.insert(question)
    }

}