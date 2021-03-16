package views

import app.Styles
import tornadofx.View
import tornadofx.addClass
import tornadofx.label
import tornadofx.vbox

class MainView : View("Pixel Art Generator") {
    override val root = vbox {
        addClass(Styles.mainView)
        label("Pixel Art Generator") {
            addClass(Styles.heading)
        }
        add(Selector())
    }
}
