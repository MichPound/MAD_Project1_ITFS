package org.wit.itfs.views

import javafx.beans.property.SimpleBooleanProperty
import org.wit.itfs.models.TourSpotMemStore
import org.wit.itfs.models.WeatherMemStore
import tornadofx.Fragment
import tornadofx.label
import tornadofx.paddingAll
import tornadofx.vbox
import java.text.SimpleDateFormat
import java.util.*

var weatherId: Long = 0

class TourSpotWeather : Fragment() {

    // Disabling extra button on top of view.
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
            label("Temperature: ${(currentWeather.main.temp - 273.15).toInt()} \u00B0 C")
            label("Feels Like: ${(currentWeather.main.feels_like - 273.15).toInt()} \u00B0 C")
            label("Temperature Low: ${(currentWeather.main.temp_min - 273.15).toInt()} \u00B0 C")
            label("Temperature High: ${(currentWeather.main.temp_max - 273.15).toInt()} \u00B0 C")
            label("Pressure: ${currentWeather.main.pressure} hPa")
            label("Humidity: ${currentWeather.main.humidity} %")
            label("Wind Speed: ${currentWeather.wind.speed} km/h")
            label("Wind Direction: ${currentWeather.wind.deg}")
            label("Wind Gusts: ${currentWeather.wind.gust} km/h")
            label("Visibility: ${currentWeather.visibility} km")
            label("Cloud Percentage: ${currentWeather.clouds.all} %")

            val cal = Calendar.getInstance()
            val tz: TimeZone = cal.timeZone
            val sdf = SimpleDateFormat("HH:mm")
            sdf.timeZone = tz

            val sunrise = sdf.format(Date(currentWeather.sys.sunrise.toLong() * 1000))
            label("Sunrise: $sunrise")

            val sunset = sdf.format(Date(currentWeather.sys.sunset.toLong() * 1000))
            label("Sunset: $sunset")

            paddingAll = 10.0
        }
    }
}
