package pl.jutupe.better.core.board

import pl.jutupe.better.core.Field
import pl.jutupe.better.core.forEach

class SimpleBoard (
        height: Int,
        width: Int
) : Board(height, width) {

    override fun checkForWin(move: Pair<Int, Int>, winningLineSize: Int): Boolean {
        if (isWinningLineHorizontal(move, winningLineSize)) return true
        if (isWinningLineVertical(move, winningLineSize)) return true
        if (isWinningLineDiagonalNegative(move, winningLineSize)) return true
        if (isWinningLineDiagonalPositive(move, winningLineSize)) return true

        return false
    }

    override fun checkForTie(): Boolean {
        forEach { field ->
            if (field.value == Field.FIELD_EMPTY_VALUE) {
                return false
            }
        }

        return true
    }

    override fun reset() {
        tiles = Array(width) { Array(height) { Field() } }
    }

    private fun isWinningLineHorizontal(lastMove: Pair<Int, Int>, length: Int): Boolean {
        return getLineLength(lastMove, 0, 1) >= length
    }

    private fun isWinningLineVertical(lastMove: Pair<Int, Int>, length: Int): Boolean {
        return getLineLength(lastMove, 1, 0) >= length
    }

    private fun isWinningLineDiagonalPositive(lastMove: Pair<Int, Int>, length: Int): Boolean {
        return getLineLength(lastMove, 1, 1) >= length
    }

    private fun isWinningLineDiagonalNegative(lastMove: Pair<Int, Int>, length: Int): Boolean {
        return getLineLength(lastMove, 1, -1) >= length
    }

    private fun getLineLength(lastMove: Pair<Int, Int>, rowMultiplier: Int, colMultiplier: Int): Int {
        val row = lastMove.first
        val column = lastMove.second

        val player = this[row, column].value
        var countRun = 1

        val movesUp = Integer.max(height - row, width - column)
        for (i in 1 .. movesUp) {
            if (isInBoundsAndSamePlayer(player, row + (i * rowMultiplier), column + (i * colMultiplier))) {
                countRun++
            } else break
        }

        val movesDown = Integer.max(row, column)
        for (i in 1 .. movesDown) {
            if (isInBoundsAndSamePlayer(player, row - (i * rowMultiplier), column - (i * colMultiplier))) {
                countRun++
            } else break
        }

        return countRun
    }

    private fun isInBoundsAndSamePlayer(player: Char, row: Int, col: Int): Boolean {
        val isOutOfBounds = row < 0 || col < 0 || row >= width || col >= height
        if (isOutOfBounds) return false
        return player == this[row, col].value
    }
}