package ru.newtonbox.wateringsystem.model

class Plant(val id: Int,
            val name: String,
            val wateringTime: Int = 0,
            val minSoilMoisture: Double = 0.0)