package com.rhcodepi.tictactoe.presentation.components

// region Import
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// endregion

// region Base Board Setup
@Composable
fun BaseBoard()
{
    // this canvas for drawing base board for tic tac toe
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    )
    {
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width / 3, y = 0f), // this line starting point for first column
            end = Offset(x = size.width / 3, y = size.height), // this line end point for first column
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 2 / 3, y = 0f), // this line starting point for second column
            end = Offset(x = size.width * 2/ 3, y = size.height), // this line end point for second column
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height / 3),// this line starting point for first row
            end = Offset(x = size.width, y = size.height / 3), // this line end point for first row
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 2 / 3), // this line starting point for second row
            end = Offset(x = size.width, y = size.height * 2 / 3), // this line end point for second row
        )

    }
}
// endregion

// region Circle and Cross Setup
@Composable
fun Circle()
{
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ){
        drawCircle(
            color = Color.Black,
            style = Stroke(width = 12f)
        )
    }
}

@Composable
fun Cross()
{
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ){
        drawLine(
            color = Color.Blue,
            strokeWidth = 15f,
            cap = StrokeCap.Round,
            start =  Offset(x = 0f,0f),
            end = Offset(x = size.width, y= size.height)
        )
        drawLine(
            color = Color.Blue,
            strokeWidth = 15f,
            cap = StrokeCap.Round,
            start = Offset(x= size.width, y = 0f),
            end = Offset(x = 0f, y = size.height),
        )
    }
}
// endregion

// region Winning Line Setup

// winning line for first and second column between
@Composable
fun WinColumnLine1()
{
    Canvas(modifier = Modifier.size(300.dp))
    {
        drawLine(
            color = Color.Green,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x= size.width *1.1f/ 6, y = 0f),
            end = Offset(x= size.width *1.1f / 6, y = size.height),
        )
    }
}

// winning line for second and third column between
@Composable
fun WinColumnLine2()
{
    Canvas(modifier = Modifier.size(300.dp))
    {
        drawLine(
            color = Color.Green,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x= size.width * 3/ 6, y = 0f),
            end = Offset(x= size.width * 3/ 6, y = size.height),
        )
    }
}

// winning line for third and last column between

@Composable
fun WinColumnLine3()
{
    Canvas(modifier = Modifier.size(300.dp))
    {
        drawLine(
            color = Color.Green,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x= size.width * 4.9f/ 6, y = 0f),
            end = Offset(x= size.width * 4.9f/ 6, y = size.height),
        )
    }
}

// winning line for first and second row between

@Composable
fun WinRowLine1()
{
    Canvas(modifier = Modifier.size(300.dp))
    {
        drawLine(
            color = Color.Green,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x= 0f, y = size.height * 1.1f/ 6),
            end = Offset(x= size.width, y = size.height * 1.1f/ 6),
        )
    }
}

// winning line for second and third row between
@Composable
fun WinRowLine2()
{
    Canvas(modifier = Modifier.size(300.dp))
    {
        drawLine(
            color = Color.Green,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x= 0f, y = size.height * 3/ 6),
            end = Offset(x= size.width, y = size.height * 3/ 6),
        )
    }
}

// winning line for third and last row between
@Composable
fun WinRowLine3()
{
    Canvas(modifier = Modifier.size(300.dp))
    {
        drawLine(
            color = Color.Green,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x= 0f, y = size.height * 4.9f/ 6),
            end = Offset(x= size.width, y = size.height * 4.9f/ 6),
        )
    }
}
// winning line for cross that equal the row and column
@Composable
fun WinDiagonalLeftLine()
{
    Canvas(modifier = Modifier.size(300.dp))
    {
        drawLine(
            color = Color.Green,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x= 0f, y = 0f),
            end = Offset(x= size.width, y = size.height),
        )
    }
}
// winning line for cross that not equal edge row and column
@Composable
fun WinDiagonalRightLine()
{
    Canvas(modifier = Modifier.size(300.dp))
    {
        drawLine(
            color = Color.Green,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x= size.width, y = 0f),
            end = Offset(x= 0f, y = size.height),
        )
    }
}

// endregion



@Composable
@Preview(showBackground = true)
fun BasePreview()
{
    BaseBoard()
}