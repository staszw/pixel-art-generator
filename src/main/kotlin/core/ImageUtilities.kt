package core

import javafx.scene.image.WritableImage
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageUtilities {
    companion object {
        fun checkSize(minHeight: Int, minWidth: Int, file: File): BufferedImage? {
            val image = ImageIO.read(file)
            return if (image != null && image.height >= minHeight && image.width >= minWidth)
                image else null
        }

        fun BufferedImage.toColors(): Array<Array<Color>> {
            val width = this.width
            val height = this.height
            val colors = Array(height) { Array(width) { Color.BLACK } }
            for (i in 0 until height) {
                for (j in 0 until width) {
                    colors[i][j] = Color(this.getRGB(j, i))
                }
            }
            return colors
        }

        fun Array<Array<Color>>.fromColors(): WritableImage {
            val height = this.size
            val width = this[0].size
            val image = WritableImage(width, height)
            for (i in 0 until height) {
                for (j in 0 until width) {
                    image.pixelWriter.setArgb(j, i, this[i][j].rgb)
                }
            }
            return image
        }
    }
}