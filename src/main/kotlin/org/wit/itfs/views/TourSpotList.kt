package org.wit.itfs.views

import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.control.TableView
import org.wit.itfs.models.TourSpotMemStore
import org.wit.itfs.models.TourSpotModel
import tornadofx.*

class TourSpotList : Fragment() {

    private val test = TourSpotMemStore()
    private val tableContent = test.findAll()
    private val data = tableContent.observable()

    override val savable = SimpleBooleanProperty(false)
    override val refreshable = SimpleBooleanProperty(false)
    override val deletable = SimpleBooleanProperty(false)

    override val root = vbox(4.0) {
        tableview(data) {
            column("TITLE", TourSpotModel::title)
            column("County", TourSpotModel::county)
            column("DESCRIPTION", TourSpotModel::desc)

            contextmenu {
                item("Update Tour Spot").action {
                    selectedItem?.apply {
                        var tourSpotDetail = TourSpotDetail()
                        tourSpotDetail.passId(id)
                        workspace.dock<TourSpotDetail>()
                    }
                }
                item("Get Weather").action {
                    selectedItem?.apply {
                        var tourSpotWeather = TourSpotWeather()
                        tourSpotWeather.weatherId(id)
                        openInternalWindow<TourSpotWeather>()
                    }
                }
            }

            onUserSelect { workspace.dock<TourSpotDetail>() }
            columnResizePolicy = TableView.UNCONSTRAINED_RESIZE_POLICY
        }
    }

    override fun onCreate() {
        workspace.dock<TourSpotCreate>()
    }
}