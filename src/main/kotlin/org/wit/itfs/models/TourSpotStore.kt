package org.wit.itfs.models

interface TourSpotStore {
    fun add(tourSpot: TourSpotModel)
    fun list()
    fun update(tourSpot: TourSpotModel)
    fun delete(tourSpot: TourSpotModel)
    fun find(index: Long): TourSpotModel?
    fun amount(): Int
}