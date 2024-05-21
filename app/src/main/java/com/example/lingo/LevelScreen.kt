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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingo.R


val Green = Color(0xFF86A043)
val Yellow = Color(0xFFFEC868)
val Orange = Color(0xFFFDA769)
val Brown = Color(0xFF473C33)
val LightGray = Color(0xFFD3D3D3)

@Preview
@Composable
fun LevelScreen() {
    Column(
        modifier = Modifier
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

            Text(
                text = "name",
                textAlign = TextAlign.End,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )

            // user icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Yellow)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.White,
            text = "Levels"
        )

        // Levels List
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LevelItem("Level 1", Yellow, R.drawable.check)
            LevelItem("Level 2", Yellow, R.drawable.check)
            LevelItem("Level 3", Orange, R.drawable.check__1_)
            LevelItem("Level 4", LightGray, R.drawable.cancel)
        }

        Image(
            painter = painterResource(id = R.drawable.back_button),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 40.dp, top = 50.dp)
                .align(Alignment.Start)
        )
    }
}

@Composable
fun LevelItem(level: String, color: Color, iconResId: Int) {
    Button(
        onClick = {},
        modifier = Modifier
            .height(90.dp)  // Made the box smaller
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 17.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Brown
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = level,
                fontSize = 20.sp,
                textAlign = TextAlign.Start, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

