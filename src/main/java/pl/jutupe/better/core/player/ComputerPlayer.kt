package pl.jutupe.better.core.player

import pl.jutupe.better.core.board.Board
import pl.jutupe.better.core.Field
import pl.jutupe.better.core.Game
import pl.jutupe.better.core.forEachIndexed

class ComputerPlayer (
        character: Char
) : Player(character) {

    override fun getMove(game: Game, board: Board) {
        val emptyMoves: MutableList<Pair<Int, Int>> = mutableListOf()

        board.forEachIndexed { row, col, field ->
            if (field.value == Field.FIELD_EMPTY_VALUE) {
                emptyMoves.add(Pair(row, col))
            }
        }

        Thread.sleep(500)

        game.onMoveTaken(emptyMoves.random(), this)
    }
}