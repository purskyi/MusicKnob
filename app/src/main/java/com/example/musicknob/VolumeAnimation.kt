package com.example.musicknob

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun VolumeBarAnimation(modifier: Modifier = Modifier,
              activeBars: Int = 0,
              barCount: Int = 10, ){





    BoxWithConstraints(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .clip(shape = RoundedCornerShape(50))){

        val barWith = remember{constraints.maxWidth / (2f * barCount) }

        var sizeState by remember { mutableStateOf(0.dp) }
        val size by animateDpAsState(targetValue = sizeState, )

        Box(modifier = Modifier
            .fillMaxHeight()
            .size(size)
            .background(Color.White),
            contentAlignment = Alignment.Center){
            for (i in 0 until barCount) {
                for (i in 0 until activeBars) {
                    sizeState += barWith.dp
                }
            }

        }
    }
}
@Preview
@Composable
fun VolumeBarAnimationPreview(){
    VolumeBarAnimation(modifier = Modifier
        .fillMaxWidth()
        .height(30.dp),
        activeBars = (10),
        barCount = 100)
}