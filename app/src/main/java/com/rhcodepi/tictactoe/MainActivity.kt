package com.rhcodepi.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.rhcodepi.tictactoe.presentation.game.GameViewModel
import com.rhcodepi.tictactoe.presentation.game.HomeViewModel
import com.rhcodepi.tictactoe.presentation.navigation.SetupNavigationGraph
import com.rhcodepi.tictactoe.ui.theme.TictactoeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TictactoeTheme {

                val homeViewModel = viewModel<HomeViewModel>()
                val gameViewModel = viewModel<GameViewModel>()
                val navController = rememberNavController()

                SetupNavigationGraph(
                    navHostController = navController,
                    homeViewModel = homeViewModel,
                    gameViewModel = gameViewModel
                )

            }
        }
    }
}