package pl.jutupe.better.core

class Field (
        var value: Char = FIELD_EMPTY_VALUE
) {
    fun isEmpty(): Boolean = value == FIELD_EMPTY_VALUE

    companion object {
        const val FIELD_EMPTY_VALUE = ' '
        const val INVALID_FIELD_INDEX = -1
    }
}



