package com.rhcodepi.tictactoe.presentation.game

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rhcodepi.tictactoe.presentation.components.BoardCellValue
import com.rhcodepi.tictactoe.presentation.components.WinningType
import com.rhcodepi.tictactoe.presentation.input.UserInput

class GameViewModel : ViewModel()
{

    // state value get from GameState data class
    var state by mutableStateOf(GameState())

    // region Map for cellValue
    val mapBoard: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE,
    )
    // endregion

    // region Check User Input
    fun onClick(userInput: UserInput){
        when(userInput){
            is UserInput.BoardInput -> {
                // add value to board
                addValueToBoard(userInput.cellNo)
            }
            UserInput.PlayAgainButtonInput -> {
                playAgain()
            }
        }
    }
    // endregion

    // region Input Add Value to Map
    // for adding value the map object and checking who is winning
    private fun addValueToBoard(cellNO : Int)
    {
        if(mapBoard[cellNO] != BoardCellValue.NONE) // is board full return false
            return
        if(state.cellValue == BoardCellValue.CROSS){
            mapBoard[cellNO] = BoardCellValue.CROSS
            if(checkForWinning(BoardCellValue.CROSS))
            {
                state = state.copy(
                    cellValueText = "WIN X",
                    cellValue = BoardCellValue.NONE,
                    hasWon = true,
                    crossPlayerScore = state.crossPlayerScore + 1
                )
            } else if(boardIsFull()){
                state = state.copy(
                    drawScore = state.drawScore + 1,
                    hasWon = false,
                    cellValueText = "Draw"
                )
            } else {
                state = state.copy(
                    cellValueText = "Now Playing : O",
                    cellValue = BoardCellValue.CIRCLE,
                )
            }

        } // check cross player for have been won
        else if(state.cellValue == BoardCellValue.CIRCLE){
            mapBoard[cellNO] = BoardCellValue.CIRCLE
            if(checkForWinning(BoardCellValue.CIRCLE)){
                state = state.copy(
                    cellValueText = "WON O",
                    cellValue = BoardCellValue.NONE,
                    circlePlayerScore = state.circlePlayerScore + 1,
                    hasWon = true
                )
            } else if(boardIsFull())
            {
                state = state.copy(
                    cellValueText = "Draw",
                    drawScore = state.drawScore + 1,
                    hasWon = false
                )
            } else {
                state = state.copy(
                    cellValue = BoardCellValue.CROSS,
                    cellValueText = "Now Playing : X"
                )
            }
        } // check circle player for have been won
    }
    // endregion

    // region Input Play Again
    // for restart the button
    private fun playAgain(){
        mapBoard.forEach { (i, _) ->
            mapBoard[i] = BoardCellValue.NONE
        }

        state = state.copy(
            cellValue = BoardCellValue.CROSS,
            cellValueText = "Now Playing : X",
            winningType = WinningType.NONE,
            hasWon = false
        )
    }
    // endregion

    // region Check Board is Full
    private fun boardIsFull() : Boolean
    {
        if(mapBoard.containsValue(BoardCellValue.NONE)) return false
        return true;
    }
    // endregion

    //region Check for Winning
    private fun checkForWinning(value : BoardCellValue) : Boolean{
        // creating winning state list has position and enum info
        val boardWinningState = listOf(
            listOf(1, 2, 3, WinningType.FirstRow),
            listOf(4, 5, 6, WinningType.SecondRow),
            listOf(7, 8, 9, WinningType.ThirdRow),
            listOf(1, 4, 7, WinningType.FirstColumn),
            listOf(2, 5, 8, WinningType.SecondColumn),
            listOf(3, 6, 9, WinningType.ThirdColumn),
            listOf(1, 5, 9, WinningType.LeftDiagonal),
            listOf(3, 5, 7, WinningType.RightDiagonal)
        )
        // using winning state list and check with value and return true when anyone is win
        for((firstIndex, secondIndex, thirdIndex, winningType) in boardWinningState){
            if(mapBoard[firstIndex] == value && mapBoard[secondIndex] == value && mapBoard[thirdIndex] == value ){
                state = state.copy(winningType = winningType as WinningType)
                return true
            }
        }
        return false
    }
    // endregion

}