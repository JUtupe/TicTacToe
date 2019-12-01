package pl.jutupe.better.implementations.windowed

import pl.jutupe.better.core.board.Board
import pl.jutupe.better.core.Game
import pl.jutupe.better.core.player.Player

class WindowedGame (
        board: Board,
        players: List<Player>,
        winningLineSize: Int = 3,
        private val gameView: GameView
) : Game(board, players, winningLineSize) {

    override fun display(board: Board) = gameView.display(board)

    override fun onGameWon(winner: Player) = gameView.onGameWon(winner)

    override fun onGameTie() = gameView.onGameTie()

    override fun onInvalidMove() = gameView.onInvalidMove()

    interface GameView {
        fun display(board: Board)
        fun onGameWon(winner: Player)
        fun onGameTie()
        fun onInvalidMove()
    }
}