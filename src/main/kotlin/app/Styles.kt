package app

import javafx.geometry.Pos
import javafx.scene.text.FontPosture
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles: Stylesheet() {
    companion object {
        val mainView by cssclass()
        val vbox by cssclass()
        val heading by cssclass()
        val image by cssclass()
    }
    init {
        mainView {
            padding = box(30.px)
            alignment = Pos.CENTER
        }
        vbox {
            spacing = 5.px
        }
        heading {
            fontSize = 30.px
        }
        image {
            fitToHeight = true
            fitToWidth = true
            maxWidth = 300.px
            maxHeight = 300.px
        }
    }
}
