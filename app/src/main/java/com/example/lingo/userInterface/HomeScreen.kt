package com.example.lingo.userInterface
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.layout.Column
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lingo.R
import com.example.lingo.room.Course
import com.example.lingo.ui.theme.Brown
import com.example.lingo.ui.theme.Green
import com.example.lingo.ui.theme.Yellow
import androidx.navigation.NavController

@Composable
fun HomeScreen(homeViewModel: HomeViewModel,
               loginViewModel: LoginViewModel,
               navController: NavHostController,
) {
    val username by loginViewModel.username.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val courses by homeViewModel.courses.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.getCourses()
    }

    val onLogout: () -> Unit = {
        coroutineScope.launch {
            loginViewModel.clearUser()
                navController.navigate("Login")
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Green),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(text = username,
                textAlign = TextAlign.End,
                color = Color.White,
                modifier= Modifier.padding(10.dp))

            //user icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Yellow)
            ){
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(modifier = Modifier
            .align(Alignment.Start)
            .padding(horizontal = 20.dp, vertical = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.White,
            text = "Courses")

        val flags = listOf(
            R.drawable.spain,
            R.drawable.france,
            R.drawable.turkey,
            R.drawable.greece,
            R.drawable.china
        )

        val size = minOf(courses.size, flags.size)

        courses.take(size).forEachIndexed { index:Int, course:Course->
            val flag = flags[index]
            CourseItem(
                course = course,
                flag = flag,
                homeViewModel,
                navController = navController
            )

        }

        Image(
            painter = painterResource(id = R.drawable.logout),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 40.dp, top = 50.dp)
                .align(Alignment.Start)
                .clickable {
                    onLogout()
                }
        )
    }
}

@Composable
fun CourseItem(course: Course,
               flag: Int,
               homeViewModel: HomeViewModel,
               navController: NavController
) {

    Button(
        onClick = { navController.navigate("Questions/{course.id}")
            homeViewModel.setSelectedCourse(course)
        },
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(vertical = 7.dp, horizontal = 17.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Yellow,
            contentColor = Brown
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = course.name,
                fontSize = 17.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f) // we use weight so that the remaining space is given to text
                    .padding(start = 8.dp)
            )
            Image(
                painter = painterResource(id = flag),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
