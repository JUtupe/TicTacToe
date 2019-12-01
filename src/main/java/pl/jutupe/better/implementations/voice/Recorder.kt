package pl.jutupe.better.implementations.voice

import java.io.File
import javax.sound.sampled.*
import kotlin.concurrent.thread


object Recorder {
    private val wavFile = File("playerMove.wav")
    private val fileType = AudioFileFormat.Type.WAVE
    private val format = AudioFormat(16000.0f, 16, 2, true, true)
    private val info = DataLine.Info(TargetDataLine::class.java, format)
    private val line = AudioSystem.getLine(info) as TargetDataLine

    fun recordMicrophone(duration: Long, callback: ((File) -> Unit)) {
        thread(
                isDaemon = true
        ) {
            try {
                println("Recording started")
                Thread.sleep(duration)
                println("Recording finished")

            } catch (ie: InterruptedException) {
                println(ie.message)
            } finally {
                line.stop()
                line.close()

                callback(wavFile)
            }
        }

        try {
            if (!AudioSystem.isLineSupported(info)) {
                println("Line not supported")
            } else {
                line.open(format)
                line.start()
                AudioSystem.write(AudioInputStream(line), fileType, wavFile)
            }
        } catch (lue: LineUnavailableException) {
            println(lue.message)
        }
    }
}