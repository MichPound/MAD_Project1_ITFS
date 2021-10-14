package org.wit.itfs.controllers

import org.wit.itfs.models.TourSpotMemStore
import org.wit.itfs.models.TourSpotModel
import org.wit.itfs.models.WeatherMemStore

class TourSpotController {

    private val tourSpots = TourSpotMemStore()
    private val weather = WeatherMemStore()

    fun start() {

        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> addTourSpot()
                2 -> listTourSpots()
                3 -> updateTourSpot()
                4 -> deleteTourSpot()
                5 -> getGeneralWeather()
                6 -> getFullWeather()
                -0 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -0)
    }

    private fun menu(): Int {

        val option: Int

        println("MAIN MENU")
        println(" 1. Add Tourist Spot")
        println(" 2. List Tourist Spots")
        println(" 3. Update Tourist Spot")
        println(" 4. Delete Tourist Spot")
        println(" 5. General Weather")
        println(" 6. Full Weather")
        println(" -0. Exit")
        println()
        print("Enter your option : ")
        val input: String? = readLine()!!
        option = if (input?.toIntOrNull() != null && input.isNotEmpty())
            input.toInt()
        else
            -9
        return option
    }

    private fun addTourSpot() {
        val newSpot = TourSpotModel()

        var tempTitle = ""
        var tempCounty = ""
        var tempDesc = ""
        var tempLat = ""
        var tempLong = ""

        while (tempTitle.isEmpty()) {
            print("Enter a Title : ")
            tempTitle = readLine()!!
        }
        newSpot.title = tempTitle

        while (tempCounty.isEmpty()) {
            print("Enter a County : ")
            tempCounty = readLine()!!
        }
        newSpot.county = tempCounty

        while (tempDesc.isEmpty()) {
            print("Enter a Description : ")
            tempDesc = readLine()!!
        }
        newSpot.desc = tempDesc

        while (tempLat.isEmpty()) {
            print("Enter a Latitude : ")
            tempLat = readLine()!!
        }
        newSpot.lat = tempLat.toDouble()

        while (tempLong.isEmpty()) {
            print("Enter a Longitude : ")
            tempLong = readLine()!!
        }
        newSpot.long = tempLong.toDouble()


        print("Enter optional contact Information : ")
        val tempContact = readLine()!!
        if (tempContact == "") newSpot.contactInfo = "N/A" else newSpot.contactInfo = tempContact

        print("Enter optional opening time : ")
        val tempOpen = readLine()!!
        if (tempOpen == "") newSpot.openTime = 00.00 else newSpot.openTime = tempOpen.toDouble()

        print("Enter optional closing time : ")
        val tempClose = readLine()!!
        if (tempClose == "") newSpot.closingTime = 00.00 else newSpot.closingTime = tempClose.toDouble()

        print("Ticket/Booking needed? \n\t Y/N : ")
        val tempTicket = readLine()!!
        newSpot.ticket = tempTicket == "Y" || tempTicket == "y"

        tourSpots.add(newSpot)
    }

    private fun listTourSpots() {
        println()
        if (tourSpots.amount() != 0) tourSpots.list() else println("There is currently no Tourist Spots available!")
    }

    private fun updateTourSpot() {

        val spot = selectSpot()
        val update = TourSpotModel()

        if (spot != null) {
            update.id = spot.id

            print("Enter a new Title for (${spot.title}) : ")
            val tempTitle = readLine()!!
            if (tempTitle.isNotEmpty()) {
                update.title = tempTitle
                println("Tourist Spot title updated")
            } else {
                update.title = spot.title
                println("Tourist Spot title not updated")
            }

            print("Enter a new County for (${spot.county}) : ")
            val tempCounty = readLine()!!
            if (tempCounty.isNotEmpty()) {
                update.county = tempCounty
                println("Tourist Spot County updated")
            } else {
                update.county = spot.county
                println("Tourist Spot County not updated")
            }

            print("Enter a new Description for (${spot.desc}) : ")
            val tempDesc = readLine()!!
            if (tempDesc.isNotEmpty()) {
                update.desc = tempDesc
                println("Tourist Spot description updated")
            } else {
                update.desc = spot.desc
                println("Tourist Spot description not updated")
            }

            print("Enter a new Latitude for (${spot.lat}) : ")
            val tempLat = readLine()!!
            if (tempLat.isNotEmpty()) {
                update.lat = tempLat.toDouble()
                println("Tourist Spot Latitude updated")
            } else {
                update.lat = spot.lat
                println("Tourist Spot Latitude not updated")
            }

            print("Enter a new Longitude for (${spot.long}) : ")
            val tempLong = readLine()!!
            if (tempLong.isNotEmpty()) {
                update.long = tempLong.toDouble()
                println("Tourist Spot Longitude updated")
            } else {
                update.long = spot.long
                println("Tourist Spot Longitude not updated")
            }

            print("Enter new optional contact information for (${spot.contactInfo}) : ")
            val tempContact = readLine()!!
            if (tempContact.isNotEmpty()) {
                update.contactInfo = tempContact
                println("Tourist Spot optional contact information updated")
            } else {
                update.contactInfo = spot.contactInfo
                println("Tourist Spot optional contact information not updated")
            }

            print("Enter a new optional opening time for (${spot.openTime}) : ")
            val tempOpen = readLine()!!
            if (tempOpen.isNotEmpty()) {
                update.openTime = tempOpen.toDouble()
                println("Tourist Spot optional opening time updated")
            } else {
                update.openTime = spot.openTime
                println("Tourist Spot optional opening time not updated")
            }

            print("Enter a new optional closing time for (${spot.closingTime}) : ")
            val tempClose = readLine()!!
            if (tempClose.isNotEmpty()) {
                update.closingTime = tempClose.toDouble()
                println("Tourist Spot optional closing time updated")
            } else {
                update.closingTime = spot.closingTime
                println("Tourist Spot optional closing time not updated")
            }

            print("Update whether ticket needed (${spot.ticket}), T/F: ")
            val tempTicket = readLine()!!
            if (tempTicket.isNotEmpty()) {
                update.ticket = tempTicket == "T" || tempTicket == "t"
                println("Tourist Spot ticket updated")
            } else {
                update.ticket = spot.ticket
                println("Tourist Spot ticket not updated")
            }

            tourSpots.update(update)

        } else {
            println("Tourist Spot not found...")
        }

    }

    private fun deleteTourSpot() {
        val selected = selectSpot()

        if (selected != null) {

            print("Are you sure you want to delete this tourist spot? \n" + "\t Y/N :  ")

            val choice = readLine()!!
            if (choice == "Y" || choice == "y") {
                try {
                    tourSpots.delete(selected)
                    println("Removed Successfully!")
                } catch (e: Exception) {
                    throw Exception("Error while removing...")
                }
            } else {
                println("Deletion cancelled!")
            }
        } else {
            println("Tourist Spot not found...")
        }
    }

    private fun selectSpot(): TourSpotModel? {
        listTourSpots()
        return if (tourSpots.amount() != 0) {
            print("\nEnter Title of Tourist Spot: ")
            val index = readLine()!!
            tourSpots.find(index.toLong())
        } else {
            null
        }
    }

    private fun getFullWeather() {
        val spot = selectSpot()

        if (spot != null) {
            val currentWeather = weather.getWeather(spot)

            println("Status: ${currentWeather.weather[0].main}")
            println("Description: ${currentWeather.weather[0].description}")
            println("Temperature: ${(currentWeather.main.temp - 273.15).toInt()}")
            println("Feels Like: ${(currentWeather.main.feels_like - 273.15).toInt()}")
            println("Temperature Low: ${(currentWeather.main.temp_min - 273.15).toInt()}")
            println("Temperature High: ${(currentWeather.main.temp_max - 273.15).toInt()}")
            println("Pressure: ${currentWeather.main.pressure}")
            println("Humidity: ${currentWeather.main.humidity}")
            println("Wind Speed: ${currentWeather.wind.speed}")
            println("Wind Direction: ${currentWeather.wind.deg}")
            println("Wind Gusts: ${currentWeather.wind.gust}")
            println("Visibility: ${currentWeather.visibility}")
            println("Cloud Percentage: ${currentWeather.clouds.all}")
            println("Sunrise: ${currentWeather.sys.sunrise}")
            println("Sunset: ${currentWeather.sys.sunset}")

        } else {
            println("Tourist Spot not found...")
        }
    }

    private fun getGeneralWeather() {
        val spot = selectSpot()

        if (spot != null) {
            val currentWeather = weather.getWeather(spot)

            println("Status: ${currentWeather.weather[0].main}")
            println("Description: ${currentWeather.weather[0].description}")
            println("Temperature: ${(currentWeather.main.temp - 273.15).toInt()}")
            println("Feels Like: ${(currentWeather.main.feels_like - 273.15).toInt()}")

        } else {
            println("Tourist Spot not found...")
        }
    }
}
