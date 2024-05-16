package com.example.lingo.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingo.R

@Preview
@Composable
fun HomeScreen() {
    Column(
        Modifier
            .padding(24.dp)
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
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(text = "name",
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


        // Courses List
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CourseItem("Spanish", painterResource(id = R.drawable.spain))
            CourseItem("French", painterResource(id = R.drawable.france))
            CourseItem("Turkish", painterResource(id = R.drawable.turkey))
            CourseItem("Greek", painterResource(id = R.drawable.greece))
            CourseItem("Chinese", painterResource(id = R.drawable.china))
        }

        Image(
            painter = painterResource(id = R.drawable.logout),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 40.dp, top = 50.dp)
                .align(Alignment.Start)
        )
    }
}

@Composable
fun CourseItem(language: String, flag: Painter) {
    Button(
        onClick = {},
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
                text = language,
                fontSize = 17.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f) // we use weight so that the remaining space is given to text
                    .padding(start = 8.dp)
            )
            Image(
                painter = flag,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
