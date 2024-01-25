@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class)

package com.rhcodepi.tictactoe.presentation.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rhcodepi.tictactoe.R
import com.rhcodepi.tictactoe.presentation.components.BaseBoard
import com.rhcodepi.tictactoe.presentation.components.BoardCellValue
import com.rhcodepi.tictactoe.presentation.components.Circle
import com.rhcodepi.tictactoe.presentation.components.Cross
import com.rhcodepi.tictactoe.presentation.components.WinColumnLine1
import com.rhcodepi.tictactoe.presentation.components.WinColumnLine2
import com.rhcodepi.tictactoe.presentation.components.WinColumnLine3
import com.rhcodepi.tictactoe.presentation.components.WinDiagonalLeftLine
import com.rhcodepi.tictactoe.presentation.components.WinDiagonalRightLine
import com.rhcodepi.tictactoe.presentation.components.WinRowLine1
import com.rhcodepi.tictactoe.presentation.components.WinRowLine2
import com.rhcodepi.tictactoe.presentation.components.WinRowLine3
import com.rhcodepi.tictactoe.presentation.components.WinningType
import com.rhcodepi.tictactoe.presentation.input.UserInput
import com.rhcodepi.tictactoe.presentation.navigation.Screen

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    navController: NavController
)
{
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(id = R.color.light_blue),
                        colorResource(id = R.color.dark_blue)
                    )
                )
            )
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { viewModel.onClick(
                    UserInput.PlayAgainButtonInput
                ) },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(horizontal = 15.dp)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.button_Green),
                ),
                shape = RoundedCornerShape(15.dp),
            ) {
                Text(text = "Play Again")
            }

            Button(
                onClick = {
                    viewModel.onClick(UserInput.ReturnToHome)
                    viewModel.goToHome(
                        Screen.Home.route,
                        navController
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.button_Green),
                ),
                shape = RoundedCornerShape(15.dp),
                ) {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            GameScreenText(
                text = state.cellValueText,
                fontSize = 22.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(14.dp)
                )
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ){
            BaseBoard()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .aspectRatio(1f),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center,
            )
            {
                viewModel.mapBoard.forEach{(cellNO, boardValue) ->
                    item{
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    enabled = !state.changeTurn
                                ) {
                                    viewModel.onClick(
                                        UserInput.BoardInput(cellNO)
                                    )
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AnimatedVisibility(
                                visible = viewModel.mapBoard[cellNO] != BoardCellValue.NONE,
                                enter = scaleIn(tween(1000))
                            ) {
                                if(boardValue == BoardCellValue.CROSS)
                                {
                                    Cross()
                                }else if(boardValue == BoardCellValue.CIRCLE)
                                {
                                    Circle()
                                }
                            }
                        }
                    }
                }
            }
            Column(
                modifier= Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(2000))
                ) {
                    DrawWinningLine(state = state)
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            GameScreenText(text = "Score X: ${state.crossPlayerScore}")
            GameScreenText(text = "Score O: ${state.circlePlayerScore}")
            GameScreenText(text = "Draw Score : ${state.drawScore}")
        }

    }
}

@Composable
fun GameScreenText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp
)
{
    Text(
        modifier = modifier
            .padding(4.dp),
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
        color = Color.White,
        overflow = TextOverflow.Visible,
        fontStyle = FontStyle.Italic,
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(x = 5.2f, y = 5.2f)
            ),
            textMotion =  TextMotion.Animated
        )
    )
}

@Composable
fun DrawWinningLine(
    state: GameState
)
{
    when(state.winningType) {
        WinningType.NONE -> {}
        WinningType.FirstColumn -> WinColumnLine1()
        WinningType.SecondColumn -> WinColumnLine2()
        WinningType.ThirdColumn -> WinColumnLine3()
        WinningType.FirstRow -> WinRowLine1()
        WinningType.SecondRow -> WinRowLine2()
        WinningType.ThirdRow -> WinRowLine3()
        WinningType.LeftDiagonal -> WinDiagonalLeftLine()
        WinningType.RightDiagonal -> WinDiagonalRightLine()
    }
}



@Composable
@Preview
fun GameScreenPreview()
{
    GameScreen(
        viewModel = GameViewModel(),
        navController = rememberNavController()
    )
}
