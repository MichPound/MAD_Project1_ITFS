package org.wit.itfs.models

import APIKEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

private val listType = object : TypeToken<WeatherModel>() {}.type

class WeatherMemStore : WeatherStore {
    override fun getWeather(tourSpot: TourSpotModel): WeatherModel {
        val lat = tourSpot.lat
        val long = tourSpot.long
        val apikey = APIKEY

        var json = ""
        try {
            json = URL("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$long&appid=$apikey")
                .readText()
        } catch (e: Exception) {
            println(e)
        }

        println("THIS: $json")

        return Gson().fromJson(json, listType)

    }
}