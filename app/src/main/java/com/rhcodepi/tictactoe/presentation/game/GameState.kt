package com.rhcodepi.tictactoe.presentation.game

import com.rhcodepi.tictactoe.presentation.components.BoardCellValue
import com.rhcodepi.tictactoe.presentation.components.WinningType

data class GameState(
    val crossPlayerScore: Int = 0,
    val circlePlayerScore: Int = 0,
    val isPlayWithAI: Boolean = false,
    val changeTurn: Boolean = false,
    val drawScore: Int = 0,
    val hasWon: Boolean = false,
    val winningType: WinningType = WinningType.NONE,
    val cellValue: BoardCellValue = BoardCellValue.CROSS,
    val cellValueText: String = "Now Playing : X "
)
