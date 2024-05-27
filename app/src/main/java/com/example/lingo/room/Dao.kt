package com.example.lingo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insert(user:User)

    @Query("SELECT * FROM users WHERE id=:id")
    fun getUserById(id:Int):User

    @Query("SELECT * FROM users WHERE username=:username")
    fun getUserByName(username:String):User

    @Query("SELECT username FROM users WHERE id=:id")
    fun getUsername(id:Int):String
}
@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: Question)

    @Query("SELECT * FROM questions WHERE courseId=:courseId")
    fun getQuestionsByCourse(courseId: Int):List<Question>

    @Query("SELECT * FROM questions")
    suspend fun getQuestions():List<Question>
}

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course:Course)

    @Query("SELECT name FROM courses")
    fun getCourseNames(): List<String>

    @Query("SELECT * FROM courses")
    suspend fun getCourses(): List<Course>

}