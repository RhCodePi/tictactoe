package com.rhcodepi.tictactoe.presentation.game

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rhcodepi.tictactoe.R
import com.rhcodepi.tictactoe.presentation.navigation.Screen
import com.rhcodepi.tictactoe.ui.theme.TictactoeTheme

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    gameViewModel: GameViewModel
) {
    val activity = (LocalContext.current as? Activity)

    val animVisiblity = remember { MutableTransitionState(false) }
        .apply {
            targetState = true
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.light_blue),
                        colorResource(R.color.dark_blue)
                    )
                )
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = "Tic Tac Toe",
            fontSize = 65.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center,
        )
        AnimatedVisibility(
            visibleState = animVisiblity,
            enter = fadeIn(
                animationSpec = tween(2000)
            ),
            exit = fadeOut(
                animationSpec = tween(2000)
            )
        ) {
        }
        Button(
            onClick = {
                      homeViewModel.playWithFriend(
                          Screen.Game.route, navController
                      )
            },
            modifier = Modifier
                .padding(horizontal = 120.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.deep_blue),
            )
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Play",
                color = Color.White,
            )
        }
        Button(
            onClick = {
                homeViewModel.playWithAI(
                    gameViewModel,
                    Screen.Game.route,
                    navController
                )
            },
            modifier = Modifier
                .padding(horizontal = 100.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_Green),
            )
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Play With AI",
                color = Color.White
            )
        }
        Button(
            onClick = {
                homeViewModel.exitGame(activity)
            },
            modifier = Modifier
                .padding(horizontal = 120.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
            )
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Exit",
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TictactoeTheme {
        HomeScreen(
            navController = rememberNavController(),
            homeViewModel = HomeViewModel(),
            gameViewModel = GameViewModel()
        )
    }
}
