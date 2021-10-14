package org.wit.itfs.models

data class WeatherModel(
    var coord: Coord,
    var weather: List<Weather>,
    var base: String,
    var main: Main,
    var visibility: Int,
    var wind: Wind,
    var clouds: Clouds,
    var dt: Int,
    var sys: Sys,
    var timezone: Int,
    var id: Int,
    var name: String,
    var cod: Int
)

data class Coord(
    var lon: Double,
    var lat: Double
)

data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)

data class Main(
    var temp: Double,
    var feels_like: Double,
    var temp_min: Double,
    var temp_max: Double,
    var pressure: Int,
    var humidity: Int
)

data class Wind(
    var speed: Double,
    var deg: Int,
    var gust: Double
)

data class Clouds(
    var all: Int
)

data class Sys(
    var type: Int,
    var id: Int,
    var message: Double,
    var country: String,
    var sunrise: Int,
    var sunset: Int
)