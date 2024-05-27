package com.example.lingo.userInterface

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lingo.R
import com.example.lingo.room.Question
import com.example.lingo.ui.theme.Brown
import com.example.lingo.ui.theme.Green
import com.example.lingo.ui.theme.Orange

@Composable
fun QuestionScreen(
    questionsViewModel: QuestionsViewModel,
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    val courseQuestions by questionsViewModel.courseQuestions.collectAsState()
    val selectedCourse by homeViewModel.course.collectAsState()
    val selectedOptions = remember { mutableStateOf(mutableMapOf<Int, String>()) }
    var totalGrade by remember { mutableStateOf(0) }

    LaunchedEffect(selectedCourse) {
        selectedCourse?.let { course ->
            questionsViewModel.getQuestionsByCourseID(course.id)
        }
    }

    println("selected course:${selectedCourse}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange)
                .padding(16.dp) // Padding inside the navbar
        ) {
            Text(
                text = "${selectedCourse?.name}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp,
                    color = Brown// Text color
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Display questions and options
        LazyColumn(
            modifier = Modifier.weight(1f) // Add weight to ensure it takes the available space
        ) {
            items(courseQuestions.size) { index ->
                val question = courseQuestions[index]
                val selectedOption = selectedOptions.value[question.id]

                // Display question
                Text(
                    text = question.question,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Display options
                OptionRow(
                    option = question.option1,
                    isSelected = selectedOption == question.option1,
                    onOptionSelected = {
                        selectedOptions.value = selectedOptions.value.toMutableMap().apply {
                            put(question.id, question.option1)
                        }
                        totalGrade = calculateTotalGrade(courseQuestions, selectedOptions.value)
                    }
                )
                OptionRow(
                    option = question.option2,
                    isSelected = selectedOption == question.option2,
                    onOptionSelected = {
                        selectedOptions.value = selectedOptions.value.toMutableMap().apply {
                            put(question.id, question.option2)
                        }
                        totalGrade = calculateTotalGrade(courseQuestions, selectedOptions.value)
                    }
                )
                OptionRow(
                    option = question.option3,
                    isSelected = selectedOption == question.option3,
                    onOptionSelected = {
                        selectedOptions.value = selectedOptions.value.toMutableMap().apply {
                            put(question.id, question.option3)
                        }
                        totalGrade = calculateTotalGrade(courseQuestions, selectedOptions.value)
                    }
                )
                OptionRow(
                    option = question.option4,
                    isSelected = selectedOption == question.option4,
                    onOptionSelected = {
                        selectedOptions.value = selectedOptions.value.toMutableMap().apply {
                            put(question.id, question.option4)
                        }
                        totalGrade = calculateTotalGrade(courseQuestions, selectedOptions.value)
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange) // Change this to your desired background color
                .padding(16.dp) // Padding inside the Box
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Total Grade: $totalGrade",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        color = Brown// Text color
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp) // Adjust size as needed
                        .clickable {
                            navController.navigate("Home")
                        }
                )
            }
        }
    }
}

@Composable
fun OptionRow(option: String, isSelected: Boolean, onOptionSelected: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onOptionSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onOptionSelected() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = option, style = TextStyle(fontSize = 16.sp))
    }
}

fun calculateTotalGrade(questions: List<Question>, selectedOptions: Map<Int, String>): Int {
    var totalGrade = 0
    for (question in questions) {
        if (selectedOptions[question.id] == question.answer) {
            totalGrade++
        }
    }
    return totalGrade
}
