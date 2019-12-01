package pl.jutupe.better.implementations.terminal

import pl.jutupe.better.core.board.Board
import pl.jutupe.better.core.Field
import pl.jutupe.better.core.Game
import pl.jutupe.better.core.player.Player

class TerminalPlayer(
        character: Char
) : Player(character) {

    override fun getMove(game: Game, board: Board) {
        println("$character wykonuje ruch")
        println("podaj pozycję w formacie 'x:y'")

        val playerMove = readLine()?.split(":")!!

        val x = playerMove[0].toIntOrNull() ?: Field.INVALID_FIELD_INDEX
        val y = playerMove[1].toIntOrNull() ?: Field.INVALID_FIELD_INDEX

        game.onMoveTaken(Pair(x, y), this)
    }
}