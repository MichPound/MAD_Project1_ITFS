package org.wit.itfs.models

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class TourSpotMemStore : TourSpotStore {

    private val tourSpots = ArrayList<TourSpotModel>()

    override fun add(tourSpot: TourSpotModel) {
        tourSpot.id = getId()
        tourSpots.add(tourSpot)
    }

    override fun list() {
        tourSpots.forEach { println("$it") }
    }

    override fun update(updatedTourSpot: TourSpotModel) {
        val tourSpot = find(updatedTourSpot.id)
        if (tourSpot != null) {
            tourSpot.title = updatedTourSpot.title
            tourSpot.county = updatedTourSpot.county
            tourSpot.desc = updatedTourSpot.desc
            tourSpot.lat = updatedTourSpot.lat
            tourSpot.long = updatedTourSpot.long
            tourSpot.contactInfo = updatedTourSpot.contactInfo
            tourSpot.openTime = updatedTourSpot.openTime
            tourSpot.closingTime = updatedTourSpot.closingTime
            tourSpot.ticket = updatedTourSpot.ticket
        }
    }

    override fun delete(tourSpot: TourSpotModel) {
        tourSpots.remove(tourSpot)
    }

    override fun find(index: Long): TourSpotModel? {
        return tourSpots.find { p -> p.id == index }
    }

    override fun amount(): Int {
        return tourSpots.size
    }
}