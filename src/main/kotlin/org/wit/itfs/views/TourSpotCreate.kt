package org.wit.itfs.views

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.itfs.models.TourSpotMemStore
import org.wit.itfs.models.TourSpotModel
import tornadofx.*

class TourSpotCreate : Fragment() {

    // Disabling extra button on top of view.
    override val refreshable = SimpleBooleanProperty(false)
    override val creatable = SimpleBooleanProperty(false)
    override val deletable = SimpleBooleanProperty(false)

    private val tourSpots = TourSpotMemStore()

    private val model = ViewModel()
    private val _title = model.bind {
        SimpleStringProperty()
    }
    private val _description = model.bind {
        SimpleStringProperty()
    }
    private val _county = model.bind {
        SimpleStringProperty()
    }
    private val _lat = model.bind {
        SimpleStringProperty()
    }
    private val _long = model.bind {
        SimpleStringProperty()
    }
    private val _contactInfo = model.bind {
        SimpleStringProperty("N/A")
    }
    private val _openTime = model.bind {
        SimpleStringProperty("0.0")
    }
    private val _closingTime = model.bind {
        SimpleStringProperty("0.0")
    }
    private val _ticket = model.bind {
        SimpleStringProperty("false")
    }

    override val root = scrollpane {

        form {
            fieldset(labelPosition = Orientation.VERTICAL) {
                label("Adding new Tour Spot")

                field("Title") {
                    textfield(_title).required()
                }
                field("County") {
                    textfield(_county).required()
                }
                field("Description") {
                    textarea(_description).required()
                }
                field("Latitude") {
                    textfield(_lat).required()
                }
                field("Longitude") {
                    textfield(_long).required()
                }
                field("Optional Contact Information") {
                    textfield(_contactInfo)
                }
                field("Optional Opening Time") {
                    textfield(_openTime)
                }
                field("Optional Closing Time") {
                    textfield(_closingTime)
                }
                field("Ticket/Booking (true/false)") {
                    textfield(_ticket)
                }
            }
        }
    }

    override fun onSave() {
        tourSpots.add(
            TourSpotModel(
                title = _title.value,
                county = _county.value,
                desc = _description.value,
                lat = _lat.value.toDouble(),
                long = _long.value.toDouble(),
                contactInfo = _contactInfo.value,
                openTime = _openTime.value.toDouble(),
                closingTime = _closingTime.value.toDouble(),
                ticket = _ticket.value.toBoolean()
            )
        )
        workspace.dock<TourSpotList>()
    }
}
