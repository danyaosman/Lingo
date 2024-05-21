package com.example.lingo.room

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class Users(
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

@Entity
data class QuestionList(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val questions: List<Question>,
    val levelId: Int
)

@Entity
data class Level(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val questionList: List<QuestionList>,
    val isCompleted: Boolean,
    val courseId: Int
    )

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val name: String,
    val levels: List<Level>
)