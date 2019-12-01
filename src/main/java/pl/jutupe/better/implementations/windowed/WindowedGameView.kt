package pl.jutupe.better.implementations.windowed

import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.image.Image
import pl.jutupe.better.core.Field
import pl.jutupe.better.core.board.Board
import pl.jutupe.better.core.board.SimpleBoard
import pl.jutupe.better.core.player.ComputerPlayer
import pl.jutupe.better.core.player.Player
import pl.jutupe.better.implementations.voice.VoicePlayer
import tornadofx.*

class WindowedGameView : View("TicTacToe"), WindowedGame.GameView {

    private val board = SimpleBoard(3, 3)
    private val players = listOf(WindowedPlayer('O'), ComputerPlayer('X'))
    private val game = WindowedGame(board, players, 3, this)

    override var root: Parent = gameBoard(board)

    init {
        game.start()
    }

    override fun onUndock() {
        println("onUndock")
        game.stop()
        super.onUndock()
    }

    private fun gameBoard(board: Board): Parent =
            vbox {
                alignment = Pos.CENTER
                setMinSize((board.width * 150).toDouble(), (board.height * 150).toDouble())

                for (y in 0 until board.height) {
                    hbox {
                        alignment = Pos.CENTER

                        for (x in 0 until board.width) {
                            appendGameButton(x, y)
                        }
                    }
                }
            }

    private fun Parent.appendGameButton(x: Int, y: Int) {
        button {
            setMinSize(150.0, 150.0)
            setMaxSize(150.0, 150.0)

            if (game.currentPlayer is WindowedPlayer) {
                action { game.onMoveTaken(Pair(x, y), game.currentPlayer) }
            }

            drawOnButton(board[x, y])
        }
    }


    private fun Button.drawOnButton(field: Field) {
        when (field.value) {
            Field.FIELD_EMPTY_VALUE -> imageview(Image("blank.png", 150.0, 150.0, true, true))
            'X' -> imageview(Image("X.png", 150.0, 150.0, true, true))
            'O' -> imageview(Image("O.png", 150.0, 150.0, true, true))
            else -> this.text = field.value.toString()
        }
    }

    override fun display(board: Board) {
        Platform.runLater {
            root.getChildList()?.clear()
            root = gameBoard(board)
        }
    }

    override fun onGameWon(winner: Player) {
        Platform.runLater {
            confirm(
                    title = "${winner.character} wygrał!",
                    header = "czy chcesz zacząć nową grę?") {
                game.restart()
            }
        }
    }

    override fun onGameTie() {
        Platform.runLater {
            confirm(
                    title = "Remis, nikt nie wygrał!",
                    header = "czy chcesz zacząć nową grę?"
            ) {
                game.restart()
            }
        }
    }

    override fun onInvalidMove() {
        /*Platform.runLater {
            error(
                    title = "Niepoprawny ruch",
                    header = "wykonaj poprawny ruch"
            )
        }*/
    }
}