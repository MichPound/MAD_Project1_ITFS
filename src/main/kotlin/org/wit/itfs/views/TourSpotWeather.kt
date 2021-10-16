package org.wit.itfs.views

import javafx.beans.property.SimpleBooleanProperty
import org.wit.itfs.models.TourSpotMemStore
import org.wit.itfs.models.WeatherMemStore
import tornadofx.Fragment
import tornadofx.label
import tornadofx.paddingAll
import tornadofx.vbox

var weatherId: Long = 0

class TourSpotWeather : Fragment() {

    override val savable = SimpleBooleanProperty(false)
    override val refreshable = SimpleBooleanProperty(false)
    override val deletable = SimpleBooleanProperty(false)
    override val creatable = SimpleBooleanProperty(false)

    private val tourSpots = TourSpotMemStore()
    private val weather = WeatherMemStore()

    fun weatherId(id: Long) {
        weatherId = id
    }

    override val root = vbox {
        val spot = tourSpots.find(weatherId)

        if (spot != null) {
            val currentWeather = weather.getWeather(spot)

            label("Weather")
            label("")
            label("Status: ${currentWeather.weather[0].main}")
            label("Description: ${currentWeather.weather[0].description}")
            label("Temperature: ${(currentWeather.main.temp - 273.15).toInt()}")
            label("Feels Like: ${(currentWeather.main.feels_like - 273.15).toInt()}")
            label("Temperature Low: ${(currentWeather.main.temp_min - 273.15).toInt()}")
            label("Temperature High: ${(currentWeather.main.temp_max - 273.15).toInt()}")
            label("Pressure: ${currentWeather.main.pressure}")
            label("Humidity: ${currentWeather.main.humidity}")
            label("Wind Speed: ${currentWeather.wind.speed}")
            label("Wind Direction: ${currentWeather.wind.deg}")
            label("Wind Gusts: ${currentWeather.wind.gust}")
            label("Visibility: ${currentWeather.visibility}")
            label("Cloud Percentage: ${currentWeather.clouds.all}")
            label("Sunrise: ${currentWeather.sys.sunrise}")
            label("Sunset: ${currentWeather.sys.sunset}")

            paddingAll = 10.0
        }
    }
}
