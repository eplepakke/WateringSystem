package ru.newtonbox.wateringsystem.model

class AddPlant(val id: Int,
               val name: String,
               val waterLevel: String,
               val soilMoisture: String,
               val pumpState: String,
               val serverPump: String,
               val minSoil: Double,
               val time: Int)