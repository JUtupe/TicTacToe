package pl.jutupe.better.core.board

import pl.jutupe.better.core.Field

abstract class Board (
        val height: Int,
        val width: Int
) {
    protected var tiles: Array<Array<Field>> = Array(width) { Array(height) { Field() } }

    operator fun get(row: Int, col: Int): Field {
        return tiles[row][col]
    }

    operator fun set(row: Int, col: Int, value: Char) {
        tiles[row][col].value = value
    }

    abstract fun checkForWin(move: Pair<Int, Int>, winningLineSize: Int): Boolean

    abstract fun checkForTie(): Boolean

    abstract fun reset()
}
