package com.rhcodepi.tictactoe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rhcodepi.tictactoe.presentation.game.GameScreen
import com.rhcodepi.tictactoe.presentation.game.GameViewModel
import com.rhcodepi.tictactoe.presentation.game.HomeScreen
import com.rhcodepi.tictactoe.presentation.game.HomeViewModel

@Composable
fun SetupNavigationGraph(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    gameViewModel: GameViewModel
)
{
    NavHost(
        navController = navHostController,
        startDestination = "home_screen")
    {
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(
                navController = navHostController,
                homeViewModel = homeViewModel,
                gameViewModel = gameViewModel
            )
        }
        composable(
            route = Screen.Game.route
        )
        {
            GameScreen(
                viewModel = gameViewModel,
                navController = navHostController
            )
        }

    }
}