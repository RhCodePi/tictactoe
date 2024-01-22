package com.rhcodepi.tictactoe.presentation.game

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.rhcodepi.tictactoe.presentation.input.UserInput

class HomeViewModel : ViewModel() {

    fun playWithAI(gameViewModel: GameViewModel, path: String, navController: NavController)
    {
        gameViewModel.onClick(
            UserInput.PlayWithAIButtonInput
        )
        navController.navigate(path)
    }

    fun playWithFriend(path: String, navController: NavController)
    {
        navController.navigate(path)
    }

    fun exitGame(
        activity: Activity?
    )
    {
        activity?.finish()
    }


}