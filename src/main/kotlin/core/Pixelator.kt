package core

import core.ImageUtilities.Companion.fromColors
import core.ImageUtilities.Companion.toColors
import javafx.scene.image.WritableImage
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.min
import kotlin.math.roundToInt

class Pixelator {
    companion object {
        private const val outputHeight = 100
        private const val outputWidth = 100
        private const val minimumPixel = 5
        private fun Array<Array<Color>>.pixelate(): Array<Array<Color>> {
            val height = this.size
            val width = this[0].size
            val pixelSize = Integer.max(minimumPixel, min(width / outputWidth, height / outputHeight))
            val result = Array(height) { Array(width) { Color.BLACK } }
            for (i in 0 until height step pixelSize) {
                for (j in 0 until width step pixelSize) {
                    val currentHeight = min(pixelSize, height - i)
                    val currentWidth = min(pixelSize, width - j)
                    val size = currentHeight * currentWidth
                    var sumRed = 0.0
                    var sumGreen = 0.0
                    var sumBlue = 0.0
                    for (ii in 0 until currentHeight) {
                        for (jj in 0 until currentWidth) {
                            val currentColor = this[i + ii][j + jj]
                            sumRed += currentColor.red
                            sumGreen += currentColor.green
                            sumBlue += currentColor.blue
                        }
                    }
                    val average = Color(
                        (sumRed / size).roundToInt(),
                        (sumGreen / size).roundToInt(),
                        (sumBlue / size).roundToInt()
                    )
                    for (ii in 0 until currentHeight) {
                        for (jj in 0 until currentWidth) {
                            result[i + ii][j + jj] = average
                        }
                    }
                }
            }
            return result
        }

        fun pixelate(image: BufferedImage): WritableImage {
            return image.toColors().pixelate().fromColors()
        }
    }
}