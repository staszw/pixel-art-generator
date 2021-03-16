package views

import app.Styles
import core.ImageUtilities
import core.Pixelator
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class Selector : View() {
    private var img: ImageView? = null
    override val root = vbox {
        addClass(Styles.vbox)
        button("Choose an image to make pixel art out of it") {
            action {
                val picture = chooseFile()
                val newPicture = pixelate(picture)
                if (newPicture != null) {
                    if (img == null) {
                        img = this@vbox.imageview(newPicture) {
                            isPreserveRatio = true
                            fitWidth = 500.0
                            fitHeight = 500.0
                            addClass(Styles.image)
                        }
                    } else {
                        img!!.image = newPicture
                    }
                    currentWindow!!.sizeToScene()
                }
            }
        }
    }

    private fun chooseFile(): File? {
        val fileChooser = FileChooser()
        fileChooser.title = "Choose an image"
        fileChooser.extensionFilters.add(
            FileChooser.ExtensionFilter("Images", "*.png", "*.jpg")
        )
        return fileChooser.showOpenDialog(currentStage)
    }

    private fun pixelate(picture: File?): WritableImage? {
        if (picture == null) return null
        if (!picture.canRead()) {
            notify("Can't read this file, please choose another")
            return null
        }
        val image = ImageUtilities.checkSize(100, 100, picture)
        if (image == null) {
            notify("This picture is too small - should be at least 100x100")
            return null
        }
        return Pixelator.pixelate(image)
    }

    private fun notify(text: String) {
        object : Fragment() {
            override val root = label(text)
        }.openModal()
    }
}
