package com.example.lingo.room

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String
)
data class Question(
    val question: String,
    val options: List<String>,
    val answer: String
)

@Entity
data class QuestionList(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val questions: List<Question>,
    val levelId: Int
)

@Entity
data class Level(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val questionList: List<QuestionList>,
    val isCompleted: Boolean,
    val courseId: Int
    )

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val levels: List<Level>
)