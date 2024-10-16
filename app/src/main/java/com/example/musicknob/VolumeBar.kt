package com.example.musicknob

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VolumeBar(modifier: Modifier = Modifier,
              activeBars: Int = 0,
              barCount: Int = 10, ){
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape = RoundedCornerShape(50))){
        val barWith = remember{constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier
            .border(width = 5.dp, color = Color.LightGray, shape = RoundedCornerShape(50))
            .background(Color.Black)
        ) {
            for (i in 0 until barCount){
                drawRoundRect(
                    color = if (i in 0 until activeBars) Color.White else Color.Black,
                    topLeft = Offset(i * barWith * 2f, 0f),
                    size = Size(barWith,constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }

        }
    }
}
@Preview
@Composable
fun VolumePreview(){
    VolumeBar(modifier = Modifier
        .fillMaxWidth()
        .height(30.dp),
        activeBars = (10),
        barCount = 100)
}