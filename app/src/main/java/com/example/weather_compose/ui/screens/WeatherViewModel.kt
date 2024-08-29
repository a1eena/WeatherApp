package com.example.weather_compose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_compose.network.WeatherApi
import com.example.weather_compose.network.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}

data class WeatherState(
    val result: WeatherResponse = WeatherResponse(),
    val status: Status = Status.LOADING,
    val error: String =""
)

class WeatherViewModel() : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()
    init { getWeatherData() }
    private fun getWeatherData() {
        viewModelScope.launch {
            try {
            val result = WeatherApi.retrofitService.getWeather(
                city = "lucknow",
                apiKey = "cc991a03a1066c3d95c97ad5f66a7799"
            )
            _state.update { it.copy(result = result, status = Status.SUCCESS) }
        } catch (e: Exception) {
            _state.update {
                it.copy(status = Status.ERROR,
                    error = e.message?:"Something went wrong")
                }

            }
        }
    }
}
