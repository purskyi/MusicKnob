package com.example.musicknob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicknob.ui.theme.MusicKnobTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                musicKnobApp()
        }
    }
}



@Composable
fun musicKnobApp(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center,
    ){
        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(width = 10.dp, color =  Color.White, shape = RoundedCornerShape(bottomStart = 50.dp, topEnd = 50.dp))
                .padding(start = 40.dp, end = 40.dp, top = 20.dp, bottom = 20.dp)){
            var volume by remember {
                mutableStateOf(0f)
            }
            val barCount = 100
            musicKnob(modifier = Modifier.size(100.dp)){
                volume = it
            }
            Spacer(modifier = Modifier.width(40.dp))


           /* VolumeBarAnimation(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount)*/

            VolumeBar(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount)

        }

    }
}
@Preview
@Composable
fun musicAppPreview(){
    musicKnobApp()
}