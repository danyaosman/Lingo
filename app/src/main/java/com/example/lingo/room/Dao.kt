package com.example.lingo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDao {
    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insert(user:User)

    @Query("SELECT * FROM users WHERE id=:id")
    fun getUserById(id:Int):User

    @Query("SELECT * FROM users WHERE username=:username")
    fun getUserByName(username:String):User
}

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: Question)

    @Query("SELECT * FROM questions WHERE levelId = :levelId AND courseId=:courseId")
    fun getQuestionsByLvlAndCourse(levelId:Int, courseId: Int):List<Question>
}

@Dao
interface LevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(level: Level)
    @Update
    suspend fun update(level:Level)

    @Query("SELECT * FROM levels WHERE courseid = :courseId")
    fun getCourseLevels(courseId:Int):List<Boolean>
}