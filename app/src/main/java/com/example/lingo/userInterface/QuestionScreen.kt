package com.example.lingo.userInterface

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.*

import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lingo.room.Question

@Composable
fun QuestionScreen(
    questionsViewModel: QuestionsViewModel,
    navController: NavHostController,

    ) {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize() // Ensure the Column fills the entire screen
            .fillMaxWidth()
            .background(Color(0xFFABC270))
        .padding(20.dp),

        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Level 1",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Question 1",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = question.question,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                )
        )
        Spacer(modifier = Modifier.height(16.dp))

//        LazyColumn {
//            items(question.options) { option ->
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp)
//                        .clickable { selectedOption = option },
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    RadioButton(
//                        selected = (selectedOption == option),
//                        onClick = { selectedOption = option }
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(text = option, style = TextStyle(fontSize = 16.sp))
//                }
//            }
//        }

    }
}

val question = Question(
    question = "What is your question?",
    option1 = "Option 1",
    option2 = "Option 2",
    option3 = "Option 3",
    option4 = "Option 4",
    answer = "Option 1",
    courseId = 1
)
