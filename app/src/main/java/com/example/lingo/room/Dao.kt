package com.example.lingo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDao {
    @Insert(onConflict=OnConflictStrategy.ABORT)
    suspend fun insert(user:User)

    @Query("SELECT * FROM users_table WHERE username=:username AND password=:password")
    fun getUser(username:String, password:String)
}

@Dao
interface QuestionListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionList:QuestionList)

    @Query("SELECT * FROM level_questions WHERE levelId = :levelId")
    fun getQuestionlistByLvl(levelId:Int):QuestionList
}

@Dao
interface LevelDao {
    @Update
    suspend fun update(level:Level)

    @Query("SELECT * FROM levels_table WHERE courseid = :courseId")
    fun getCourseLevels(courseId:Int):List<Boolean>
}