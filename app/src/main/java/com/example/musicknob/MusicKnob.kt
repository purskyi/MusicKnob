package com.example.musicknob

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun musicKnob(modifier: Modifier = Modifier,
              limitingAngle: Float = 2f,
              onValueChange: (Float) -> Unit = {},
){
    var rotation by remember {
        mutableStateOf(limitingAngle)
    }
    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)

    }
    var centerX by remember {
        mutableStateOf(0f)

    }
    var centerY by remember {
        mutableStateOf(0f)

    }

    Image(painterResource(id = R.drawable.music_knob),
        contentDescription = "music knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = it.boundsInWindow().size.width / 2f
                centerY = it.boundsInWindow().size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle =
                    -atan2(y = centerX - touchX, x = centerY - touchY) * (180f / PI).toFloat()
                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_UP
                    -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360f / 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false

                    }

                    else -> false
                }
            }
            .rotate(rotation))
}

@Preview
@Composable
fun musicKnobPreview(){
    musicKnob()
}