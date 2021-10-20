package org.wit.itfs.models

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class TourSpotMemStoreTest {

    private var testList = TourSpotMemStore()

    private val spot1 = TourSpotModel(
        title = "Spot One",
        county = "Waterford",
        desc = "Best Pub Ever",
        lat = 52.26,
        long = -7.12,
        contactInfo = "N/A",
        openTime = 13.0,
        closingTime = 23.0,
        ticket = false
    )

    private val spot2 = TourSpotModel(
        title = "Spot Two",
        county = "Cork",
        desc = "Beach",
        lat = 12.0,
        long = -120.0,
        contactInfo = "N/A",
        openTime = 0.0,
        closingTime = 0.0,
        ticket = false
    )

    @Test
    fun add() {
        val size = testList.amount()
        testList.add(spot1)
        assertTrue(testList.amount() == size + 1)

        testList.delete(spot1)
    }

    @Test
    fun update() {
        testList.add(spot1)
        val foundSpot = testList.findByTitle("Spot One")

        if (foundSpot != null) {
            spot2.id = foundSpot.id
        }

        testList.update(spot2)

        if (foundSpot != null) {
            assertTrue(testList.find(foundSpot.id)?.title == "Spot Two")
        }

        testList.delete(spot2)
    }

    @Test
    fun delete() {
        val size = testList.amount()
        testList.add(spot1)
        testList.add(spot2)

        assertTrue(testList.amount() == size + 2)

        testList.delete(spot2)
        assertTrue(testList.amount() == size + 1)
        testList.delete(spot1)
        assertTrue(size == testList.amount())
    }

    @Test
    fun find() {
        // Acquiring the randomized id of a tour spot.
        testList.add(spot1)
        val foundSpot = testList.findByTitle("Spot One")

        val foundSpot2 = foundSpot?.let { testList.find(it.id) }
        if (foundSpot2 != null) {
            assertTrue(foundSpot2.title == "Spot One")
        }

        testList.delete(spot1)
    }

    @Test
    fun findAll() {
        val foundSpots = testList.findAll()
        assertTrue(foundSpots.size == testList.amount())
    }

    @Test
    fun findByTitle() {
        testList.add(spot1)
        val foundSpot = testList.findByTitle("Spot One")
        if (foundSpot != null) {
            assertTrue(foundSpot.title == "Spot One")
        }

        testList.delete(spot1)
    }

    @Test
    fun amount() {
        val size = testList.amount()
        testList.add(spot1)

        assertTrue(testList.amount() == size + 1)
        testList.delete(spot1)
        assertTrue(testList.amount() == size)
    }

    @Test
    fun search() {
        testList.add(spot1)
        val searchedSpots = testList.search("Spot One")
        assertTrue(searchedSpots[0].title == "Spot One")

        testList.delete(spot1)
    }
}