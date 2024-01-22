package com.rhcodepi.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.rhcodepi.tictactoe.presentation.game.GameScreen
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TictactoeTheme {
        Greeting("Android")
    }
}