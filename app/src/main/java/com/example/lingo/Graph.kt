package com.example.lingo

import android.content.Context
import com.example.lingo.repository.Repository
import com.example.lingo.room.LingoDatabase

object Graph {
    lateinit var db:LingoDatabase private set

//    val repository by lazy{
//        Repository(
//            usersDao = db.usersDao(),
//            levelDao = db.levelDao(),
//            questionDao=db.questionDao()
//        )
//    }
    fun provide(context:Context){
        db= LingoDatabase.getDatabase(context)
    }
}