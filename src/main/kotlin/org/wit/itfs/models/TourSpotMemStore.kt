package org.wit.itfs.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.wit.itfs.helpers.fileExists
import org.wit.itfs.helpers.readFile
import org.wit.itfs.helpers.writeFile
import java.util.*

internal fun randID(): Long {
    return Random().nextLong()
}

class TourSpotMemStore : TourSpotStore {

    private var tourSpots = ArrayList<TourSpotModel>()
    private val jsonFile = "tourSpots.json"
    private val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    private val listType = object : TypeToken<java.util.ArrayList<TourSpotModel>>() {}.type

    init {
        if (fileExists(jsonFile)) {
            load()
        }
    }

    override fun add(tourSpot: TourSpotModel) {
        tourSpot.id = randID()
        tourSpots.add(tourSpot)
        save()
    }

    override fun list() {
        tourSpots.forEach {
            println("ID: ${it.id}, Title: ${it.title}, County: ${it.county}, Description: ${it.desc}, " +
                    "Latitude: ${it.lat}, Longitude: ${it.long}, Contact info: ${it.contactInfo}, " +
                    "Open Time: ${it.openTime}, Closing Time: ${it.closingTime}, Ticket:  ${it.ticket}")
        }
    }

    override fun update(updatedTourSpot: TourSpotModel) {
        var tourSpot = find(updatedTourSpot.id)

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
        save()
    }

    override fun delete(tourSpot: TourSpotModel) {
        tourSpots.remove(tourSpot)
        save()
    }

    override fun find(index: Long): TourSpotModel? {
        return tourSpots.find { p -> p.id == index }
    }

    override fun findAll(): List<TourSpotModel> {
        return tourSpots
    }

    override fun findByTitle(title: String): TourSpotModel? {
        return tourSpots.find { p -> (p.title.lowercase()).contains(title.lowercase()) }
    }

    override fun amount(): Int {
        return tourSpots.size
    }

    override fun search(search: String): List<TourSpotModel> {
        val searchedList = ArrayList<TourSpotModel>()
        tourSpots.forEach { spot ->
            try {
                if (
                    (spot.title.lowercase()).contains(search.lowercase()) ||
                    (spot.county.lowercase()).contains(search.lowercase()) ||
                    (spot.desc.lowercase()).contains(search.lowercase()) ||
                    (spot.contactInfo.lowercase()).contains(search.lowercase()) ||
                    (spot.lat == search.toDouble()) ||
                    (spot.long == search.toDouble()) ||
                    (spot.openTime == search.toDouble()) ||
                    (spot.closingTime == search.toDouble())
                ) {
                    searchedList.add(spot)
                }
            } catch (e: Exception) {
            }
        }
        return searchedList
    }

    override fun countyFilter() {
        val counties = ArrayList<String>()
        tourSpots.forEach {
            if (!counties.contains("O. ${it.county.lowercase()}")) counties.add("O. ${it.county.lowercase()}")
        }

        var tempCounty: String
        do {
            counties.forEach {
                print("    $it")
            }

            println()
            println()

            val filteredList = ArrayList<TourSpotModel>()

            for (i in 0 until counties.size){
                if (counties[i].startsWith("O")) {
                    tourSpots.forEach { spot ->
                        if ((spot.county.lowercase()).contains(counties[i].drop(3).lowercase())) {
                            filteredList.add(spot)
                        }
                    }
                }
            }
            filteredList.forEach {
                println("ID: ${it.id}, Title: ${it.title}, County: ${it.county}, Description: ${it.desc}, " +
                        "Latitude: ${it.lat}, Longitude: ${it.long}, Contact info: ${it.contactInfo}, " +
                        "Open Time: ${it.openTime}, Closing Time: ${it.closingTime}, Ticket:  ${it.ticket}")
            }

            println()

            println("-1 to Exit")
            println("Enter name of county to add/remove from filtered list: ")
            tempCounty = readLine()!!

            for (i in 0 until counties.size){
                if (counties[i].drop(3).lowercase() == (tempCounty.lowercase())) {
                    if (counties[i].startsWith("O")) {
                        counties[i] = "X${counties[i].drop(1)}"
                    } else {
                        counties[i] = "O${counties[i].drop(1)}"
                    }
                }
            }
        } while (tempCounty != "-1")
    }

    private fun save() {
        val jsonString = gsonBuilder.toJson(tourSpots, listType)
        writeFile(jsonFile, jsonString)
    }

    private fun load() {
        val jsonString = readFile(jsonFile)
        tourSpots = Gson().fromJson(jsonString, listType)
    }
}