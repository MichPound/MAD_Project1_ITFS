package org.wit.itfs.models

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

data class TourSpotModel(
    var id: Long = getId(),
    var title: String = "",
    var county: String = "",
    var desc: String = "",
    var lat: Double = 0.0,
    var long: Double = 0.0,

    var contactInfo: String = "N/A",
    var openTime: Double = 00.00,
    var closingTime: Double = 00.00,
    var ticket: Boolean = false
)