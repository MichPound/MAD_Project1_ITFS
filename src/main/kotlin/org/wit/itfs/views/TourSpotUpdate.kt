package org.wit.itfs.views

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.itfs.models.TourSpotMemStore
import org.wit.itfs.models.TourSpotModel
import tornadofx.*

var passId: Long = 0

class TourSpotDetail : Fragment() {

    // Disabling extra button on top of view.
    override val refreshable = SimpleBooleanProperty(false)
    override val creatable = SimpleBooleanProperty(false)

    private val tourSpots = TourSpotMemStore()
    private val spot = tourSpots.find(passId)

    private val model = ViewModel()

    private val _title = model.bind {
        spot?.title.toProperty()
    }
    private val _description = model.bind {
        spot?.desc.toProperty()
    }
    private val _county = model.bind {
        spot?.county.toProperty()
    }
    private val _lat = model.bind {
        spot?.lat.toProperty()
    }
    private val _long = model.bind {
        spot?.long.toProperty()
    }
    private val _contactInfo = model.bind {
        spot?.contactInfo.toProperty()
    }
    private val _openTime = model.bind {
        spot?.openTime.toProperty()
    }
    private val _closingTime = model.bind {
        spot?.closingTime.toProperty()
    }

    var _testing = model.bind {
        SimpleStringProperty("false")
    }

    fun passId(id: Long) {
        passId = id
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

                _testing = model.bind {
                    if (spot?.ticket == true) {
                        SimpleStringProperty("true")
                    } else {
                        SimpleStringProperty("false")
                    }
                }

                field("Ticket/Booking (true/false)") {
                    textfield(_testing)
                }
            }
        }
    }

    override fun onDelete() {
        if (spot != null) {
            tourSpots.delete(spot)
        }
        workspace.dock<TourSpotList>()
    }

    override fun onSave() {

        var tempTicket = false

        if (_testing.value == "true") {
            tempTicket = true
        }

        val tempSpot = TourSpotModel(
            id = passId,
            title = _title.value,
            county = _county.value,
            desc = _description.value,
            lat = _lat.value,
            long = _long.value,
            contactInfo = _contactInfo.value,
            openTime = _openTime.value,
            closingTime = _closingTime.value,
            ticket = tempTicket
        )

        tourSpots.update(tempSpot)
        workspace.dock<TourSpotList>()
    }
}
