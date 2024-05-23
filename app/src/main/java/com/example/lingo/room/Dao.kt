package com.example.lingo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

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
}

@Dao
interface CourseDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course:Course)

    @Query("SELECT name FROM courses")
    fun getCourseNames(): List<String>

    @Query("SELECT * FROM courses")
    fun getCourses(): List<Course>

}
@Dao
interface UserCoursesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userCourses: UserCourses)

    @Query("SELECT name FROM courses")
    fun getUserCourses(): List<String>
}