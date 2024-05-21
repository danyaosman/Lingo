package com.example.lingo.repository

import com.example.lingo.room.LevelDao
import com.example.lingo.room.QuestionListDao
import com.example.lingo.room.UsersDao

class Repository (
    private val usersDao:UsersDao,
    private val questionsListDao: QuestionListDao,
    private val levelDao: LevelDao
){

}