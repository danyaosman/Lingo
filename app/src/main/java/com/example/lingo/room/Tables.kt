package com.example.lingo.room

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName="users_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val username: String,
    val password: String
)
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val question: String,
    val options: List<String>,
    val answer: String
)

@Entity(tableName = "level_questions")
data class QuestionList(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val questions: List<Question>,
    val levelId: Int
)

@Entity(tableName="levels_table")
data class Level(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val isCompleted: Boolean=false,
    val courseId: Int
    )

@Entity(tableName = "courses_table")
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val name: String,
)