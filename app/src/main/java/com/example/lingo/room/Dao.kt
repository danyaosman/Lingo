package com.example.lingo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users_table WHERE username = :username")
    fun getUser(username: String): User
}

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: Course)
}

@Dao
interface LevelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(level: Level)

    @Query("SELECT * FROM levels_table WHERE courseId = :courseId")
    fun getCourseLevels(courseId: Int): List<Level>
}

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: Question)
}

@Dao
interface QuestionListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionList: QuestionList)

    @Query("SELECT * FROM level_questions WHERE levelId = :levelId")
    fun getQuestionlistByLvl(levelId: Int): QuestionList
}
