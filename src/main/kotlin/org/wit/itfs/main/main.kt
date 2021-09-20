package org.wit.itfs.main

import APIKEY
import java.net.URL

fun main(args: Array<String>) {
    getWeather(52.24, -7.13, APIKEY)
}

fun getWeather (lat: Double, lon: Double, apikey: String) {
    var weather = ""
    try {
        weather = URL("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$apikey")
            .readText(Charsets.UTF_8)
    } catch (e: Exception) {
        println(e)
    }

    println("\nThis is the weather just outside our campus: ")
    println(weather)
}