package pl.jutupe.better.core

import pl.jutupe.better.core.board.Board

fun <T> List<T>.next(current: T): T {
    val previousIndex = indexOf(current)
    return if (previousIndex == size - 1) {
        get(0)
    } else {
        get(previousIndex + 1)
    }
}

inline fun Board.forEach(action: (Field) -> Unit) {
    for (row in 0 until width) {
        for (col in 0 until height) {
            action(get(row, col))
        }
    }
}

inline fun Board.forEachIndexed(action: (Int, Int, Field) -> Unit) {
    for (row in 0 until width) {
        for (col in 0 until height) {
            action(row, col, get(row, col))
        }
    }
}