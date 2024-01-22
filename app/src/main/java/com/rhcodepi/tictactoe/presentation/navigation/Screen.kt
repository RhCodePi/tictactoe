package com.rhcodepi.tictactoe.presentation.navigation

sealed class Screen(val route: String){
    object Game: Screen("game_screen")
    object Home: Screen("home_screen")
}
