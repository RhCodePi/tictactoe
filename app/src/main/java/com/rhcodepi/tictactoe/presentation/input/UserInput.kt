package com.rhcodepi.tictactoe.presentation.input

sealed class UserInput{
    object PlayAgainButtonInput: UserInput()
    object PlayWithAIButtonInput: UserInput()
    data class BoardInput(val cellNo: Int) : UserInput()
}
