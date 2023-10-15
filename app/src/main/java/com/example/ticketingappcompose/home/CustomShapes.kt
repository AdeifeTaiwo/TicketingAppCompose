package com.example.ticketingappcompose.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DrawFirstParallelogram(modifier : Modifier = Modifier) {
    val fWidth = 140.dp.toPx()
    val fHeight = 30.dp.toPx()

    Row(modifier = modifier.wrapContentSize().height(30.dp).width(420.dp), horizontalArrangement = Arrangement.Start) {


        Box(
            modifier = Modifier
                .wrapContentSize()
                .width(140.dp)
                .weight(0.30f),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize(),
                onDraw = {
                    drawPath(color = Color(0XFF253789), path = firstTrapezium(fWidth, fHeight))
                })
            Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                drawPath(color = Color(0XFF253789), path = rightAngleTriangle(fWidth, fHeight))
            })
            Text(text = "2 listed @$1000", color = Color.White)

        }

        Box(
            modifier = Modifier
                .wrapContentSize()
                .width(140.dp)
                .weight(0.29f),
            contentAlignment = Alignment.Center
        ) {

            Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                drawPath(color = Color.Blue, path = secondTrapezium(fWidth, fHeight))
            })
            Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                drawPath(color = Color(0XFF253789), path = invertedRightAngleTriangle(fWidth, fHeight))
            })
            Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                drawPath(color = Color.Blue, path = rightAngleTriangle(fWidth, fHeight))
            })
            Text(modifier = Modifier.padding(start = 16.dp),text = "2 listed @$1000", color = Color.White, fontSize = 13.sp)
        }



        Box(
            modifier = Modifier
                .wrapContentSize()
                .width(140.dp)
                .weight(0.30f),
            contentAlignment = Alignment.Center
        ) {
            Canvas( modifier = Modifier.fillMaxSize(), onDraw = {
                drawPath(color = Color(0XFFEDEFF3), path = secondTrapezium(fWidth, fHeight))
            })
            Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                drawPath(color = Color.Blue, path = invertedRightAngleTriangle(fWidth, fHeight))
            })
            Text(text = "0 unlisted", color = Color.Black)

        }
    }

}

private fun firstTrapezium(width: Float, height: Float): Path {
    val trapeziumPath = Path().apply {
        // Moves to bottom left position
        // Add line to left corner
        moveTo(-1f, 0f)
        lineTo(width, 0f)
        lineTo(width - 60f, height)
        lineTo(0f, height)
        lineTo(0f, 0f)
        close()
    }
    return trapeziumPath
}

private fun secondTrapezium(width: Float, height: Float): Path {
    val trapeziumPath = Path().apply {
        // Moves to bottom left position
        // Add line to left corner
        moveTo(60f, 0f)
        lineTo(width, 0f)
        lineTo(width - 60f, height)
        lineTo(0f, height)
        lineTo(60f, 0f)
        close()
    }
    return trapeziumPath
}

/**
 * a triangle with hypotenus facing the left side
 */
private fun rightAngleTriangle(width: Float, height: Float): Path {
    val trapeziumPath = Path().apply {
        // Moves to bottom left position
        // Add line to left corner
        moveTo(width, 0f)
        lineTo(width, height)
        lineTo(width - 70f, height)
        close()
    }
    return trapeziumPath
}


// a unpside down triangle with hypotenus facing the right side
private fun invertedRightAngleTriangle(width:Float, height: Float): Path {
    val trianglePath = Path().apply {
        moveTo(0f, height)
        lineTo(0f, 0f)
        lineTo(60f, 0f)
        close()
    }
    
    return trianglePath
}



@Composable
fun Dp.toPx() = with(LocalDensity.current) { this@toPx.toPx() }


@Preview
@Composable
fun PreviewParallelogram() {
    Surface {
        DrawFirstParallelogram()
    }
}