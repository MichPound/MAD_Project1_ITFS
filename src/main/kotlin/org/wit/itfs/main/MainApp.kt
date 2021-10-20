package org.wit.itfs.main

import javafx.scene.Scene
import org.wit.itfs.views.TourSpotList
import tornadofx.App
import tornadofx.UIComponent
import tornadofx.Workspace

class MainApp : App(Workspace::class) {
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 425.0, 450.0)

    override fun onBeforeShow(view: UIComponent) {
        with(workspace) {
            dock<TourSpotList>()
        }
    }
}