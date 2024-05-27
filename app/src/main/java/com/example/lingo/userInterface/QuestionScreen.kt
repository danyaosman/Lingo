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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.lingo.R
import com.example.lingo.room.Question
import com.example.lingo.ui.theme.Green

@Composable
fun QuestionScreen(
    questionsViewModel: QuestionsViewModel,
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    val courseQuestions by questionsViewModel.courseQuestions.collectAsState()
    val selectedCourse by homeViewModel.course.collectAsStateWithLifecycle()

    LaunchedEffect(selectedCourse) {
        selectedCourse?.let { course ->
            questionsViewModel.getQuestionsByCourseID(course.id)
        }
    }

    println("selected course:${selectedCourse}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(Green)
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "${selectedCourse?.name}",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display questions and options
        LazyColumn {
            items(courseQuestions.size) { index ->
                val question = courseQuestions[index]
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
                    onOptionSelected = { selectedOption = question.option1 }
                )
                OptionRow(
                    option = question.option2,
                    isSelected = selectedOption == question.option2,
                    onOptionSelected = { selectedOption = question.option2 }
                )
                OptionRow(
                    option = question.option3,
                    isSelected = selectedOption == question.option3,
                    onOptionSelected = { selectedOption = question.option3 }
                )
                OptionRow(
                    option = question.option4,
                    isSelected = selectedOption == question.option4,
                    onOptionSelected = { selectedOption = question.option4 }
                )
            }

        }
        Image(
            painter = painterResource(id = R.drawable.logout),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 40.dp, top = 50.dp)
                .align(Alignment.Start)
                .clickable {
                    navController.navigate("Home")
                }
        )

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
