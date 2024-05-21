package com.example.lingo.room

import androidx.room.PrimaryKey
import androidx.room.Entity

data class Question(
    @PrimaryKey
    val id: Int,
    val question: String,
    val options: List<String>,
    val answer: String
)

@Entity(tableName="question_list")
data class QuestionList(
    val id: Int,
    val questions: List<Question>,
    val levelId: Int
)

@Entity
data class Level(
    val id: Int,
    val questionList: List<QuestionList>,
    val isCompleted: Boolean,
    val courseId: Int
    )

@Entity
data class Course(
    val id: Int,
    val name: String,
    val levels: List<Level>
)