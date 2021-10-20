package org.wit.itfs.views

import javafx.beans.property.SimpleBooleanProperty
import org.wit.itfs.models.TourSpotMemStore
import org.wit.itfs.models.TourSpotModel
import tornadofx.*

class TourSpotList : Fragment() {

    private val test = TourSpotMemStore()
    private val tableContent = test.findAll()
    private val data = tableContent.observable()

    // Disabling extra button on top of view.
    override val savable = SimpleBooleanProperty(false)
    override val refreshable = SimpleBooleanProperty(false)
    override val deletable = SimpleBooleanProperty(false)

    override val root = vbox(4.0) {

        tableview(data) {

            column("TITLE", TourSpotModel::title)
            column("County", TourSpotModel::county)
            column("Description", TourSpotModel::desc)
            column("Latitude", TourSpotModel::lat)
            column("Longitude", TourSpotModel::long)

            contextmenu {
                item("Update Tour Spot").action {
                    selectedItem?.apply {
                        val tourSpotDetail = TourSpotDetail()
                        tourSpotDetail.passId(id)
                        workspace.dock<TourSpotDetail>()
                    }
                }
                item("Get Weather").action {
                    selectedItem?.apply {
                        val tourSpotWeather = TourSpotWeather()
                        tourSpotWeather.weatherId(id)
                        openInternalWindow<TourSpotWeather>()
                    }
                }
            }
            onUserSelect { workspace.dock<TourSpotDetail>() }
        }
    }

    override fun onCreate() {
        workspace.dock<TourSpotCreate>()
    }
}