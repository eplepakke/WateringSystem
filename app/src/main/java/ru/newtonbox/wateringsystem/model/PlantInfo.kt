package ru.newtonbox.wateringsystem.model

class PlantInfo(val name: String?,
                val minSoil: Double?,
                val wateringDuration: Int?,
                val waterDevice: String?,
                val soilDevice: String?,
                val pumpDevice: String?,
                val waterLevel: Boolean?,
                val soilMoisture: Double?,
                val pumpState: String?,
                val watering: Boolean? = false)