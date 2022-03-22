package com.example.weatherapp.model

data class Weather (

    val temperature : String,
    val wind : String,
    val description : String,
    val forecast : List<Forecast>
)