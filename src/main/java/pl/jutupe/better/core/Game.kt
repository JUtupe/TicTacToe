package pl.jutupe.better.core

import pl.jutupe.better.core.board.Board
import pl.jutupe.better.core.player.Player
import java.lang.Integer.max
import kotlin.concurrent.thread

abstract class Game (
        private val board: Board,
        private val players: List<Player>,
        private val winningLineSize: Int = 3
)  {

    var currentPlayer: Player = players.first()
        private set

    var isPlaying: Boolean = true
        private set

    init {
        require(winningLineSize <= max(board.width, board.height)) {"winning line bigger than board"}
        require(winningLineSize >= 1) {"winning line can't shorter than 1"}
    }

    abstract fun display(board: Board)

    abstract fun onGameWon(winner: Player)

    abstract fun onGameTie()

    abstract fun onInvalidMove()

    fun start() {
        isPlaying = true

        requestMove()
    }

    fun restart() {
        currentPlayer = players.first()
        board.reset()
        display(board)

        start()
    }

    fun stop() {
        isPlaying = false
    }

    private fun requestMove() {
        thread {
            currentPlayer.getMove(this, board)
        }
    }

    fun onMoveTaken(move: Pair<Int, Int>, player: Player) {
        if (!isPlaying && player != currentPlayer) {
            return
        }

        if (isValidMove(move)) {
            board[move.first, move.second].value = player.character
        } else {
            onInvalidMove()
            requestMove()
            return
        }

        display(board)

        if (board.checkForWin(move, winningLineSize)) {
            isPlaying = false
            onGameWon(currentPlayer)
            return
        }

        if (board.checkForTie()) {
            isPlaying = false
            onGameTie()
            return
        }

        changeCurrentPlayer()

        requestMove()
    }

    private fun isValidMove(move: Pair<Int, Int>): Boolean =
            try {
                require(move.first >= 0 && move.first < board.width)
                require(move.second >= 0 && move.second < board.height)
                require(board[move.first, move.second].isEmpty())

                true
            } catch (e: Exception) {
                false
            }

    private fun changeCurrentPlayer() {
        currentPlayer = players.next(currentPlayer)
    }
}