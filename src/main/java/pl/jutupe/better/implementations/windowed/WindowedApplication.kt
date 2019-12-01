package pl.jutupe.better.implementations.windowed

import tornadofx.App
import tornadofx.launch

class WindowedApplication : App(WindowedGameView::class)

fun main() {
    launch<WindowedApplication>()
}