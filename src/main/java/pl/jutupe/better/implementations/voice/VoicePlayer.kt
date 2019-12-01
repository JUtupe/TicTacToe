package pl.jutupe.better.implementations.voice

import pl.jutupe.better.core.Game
import pl.jutupe.better.core.board.Board
import pl.jutupe.better.core.player.Player

/**
 * Use it instead of Terminal or Computer Player.
 *
 * It listen to you, when there is your turn!
 */
class VoicePlayer (
        character: Char
) : Player(character) {

    override fun getMove(game: Game, board: Board) {
        Recorder.recordMicrophone(recordDuration) { recordedFile ->
            val guessedMove = WitAiService.getCoordinatesForWav(recordedFile)

            game.onMoveTaken(guessedMove, this)
        }
    }

    companion object {
        const val recordDuration = 4000L
    }
}
