package org.wit.itfs.models

data class TourSpotModel(
    // Required Fields.
    var id: Long = 0,
    var title: String = "",
    var county: String = "",
    var desc: String = "",
    var lat: Double = 0.0,
    var long: Double = 0.0,
    // Optional Fields.
    var contactInfo: String = "N/A",
    var openTime: Double = 00.00,
    var closingTime: Double = 00.00,
    var ticket: Boolean = false
)