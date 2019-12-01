package pl.jutupe.better.implementations.terminal

import pl.jutupe.better.core.board.Board
import pl.jutupe.better.core.board.SimpleBoard
import pl.jutupe.better.core.player.Player
import pl.jutupe.better.core.Game
import pl.jutupe.better.core.player.ComputerPlayer

class TerminalGame (
        board: Board,
        players: List<Player>,
        winningLineSize: Int = 3
) : Game(board, players, winningLineSize) {

    override fun display(board: Board) {
        //upper number bar
        print("  ")
        for (col in 0 until board.width) {
            print(" $col  ")
        }
        println()

        for (col in 0 until board.height) {

            //row number bar
            print("$col ")

            //field value
            for (row in 0 until board.width) {
                val char = board[row, col].value
                print(" $char |")
            }

            println()

            print("  ")
            for (row in 0 until board.width) {
                print("---+")
            }

            println()
        }
    }

    override fun onGameWon(winner: Player) {
        println("${winner.character} wygrał grę, gratulacje!")
    }

    override fun onGameTie() {
        println("Remis, brak zwyciężcy!")
    }

    override fun onInvalidMove() {
        println("Niepoprawny ruch")
    }
}

fun main() {
    val board = SimpleBoard(3, 3)
    val players = listOf(TerminalPlayer('O'), TerminalPlayer('X'))
    val game = TerminalGame(board, players, 3)

    game.start()
}