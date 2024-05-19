package com.example.lingo;
import androidx.annotation.DrawableRes;

data class Question(
    val question: String,
    val options: List<String>,
    val answer: String,
    val id: Int
)