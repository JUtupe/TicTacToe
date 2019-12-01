package pl.jutupe.better.core.player

import pl.jutupe.better.core.board.Board
import pl.jutupe.better.core.Game

abstract class Player (
        val character: Char
) {

    abstract fun getMove(game: Game, board: Board)
}