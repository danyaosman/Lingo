package com.example.lingo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, Question::class, Level::class, Course::class],
    version = 1,
    exportSchema = false
)
abstract class LingoDatabase:RoomDatabase () {
    abstract fun usersDao():UsersDao
    abstract fun questionDao():QuestionDao
    abstract fun levelDao():LevelDao

    companion object{
        var INSTANCE:LingoDatabase? = null
        fun getDatabase(context:Context):LingoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    LingoDatabase::class.java,
                    "lingo_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}