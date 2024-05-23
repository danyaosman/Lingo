package com.example.lingo.room

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName="users")
data class User(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val username: String,
    val password: String
)

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: String,
    val courseId: Int
)


@Entity(tableName = "courses")
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int=1,
    val name: String,
)

@Entity(tableName = "user_courses")
data class UserCourses(
    @PrimaryKey
    val userId:Int,
    val courseId: Int
)