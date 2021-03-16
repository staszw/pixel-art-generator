package views

import tornadofx.View
import tornadofx.label
import tornadofx.vbox

class MainView : View("Pixel Art Generator") {
    override val root = vbox {
        label("Pixel Art Generator")
        add(Selector())
    }
}
