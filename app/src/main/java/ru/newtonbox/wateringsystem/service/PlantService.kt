package ru.newtonbox.wateringsystem.service

import okhttp3.ResponseBody
import retrofit2.http.Field
import ru.newtonbox.wateringsystem.model.*

interface PlantService {
    suspend fun getPlantList(): List<Plant>
    suspend fun getPlantInfo(plantId: Int): PlantInfo
    suspend fun sendWatering(plantId: Int): WateringResult
    suspend fun getSoilMoisture(plantId: Int): List<SoilMoisture>
    suspend fun getWaterLevel(plantId: Int): List<WaterLevel>
    suspend fun getPumpState(plantId: Int): List<PumpState>
    suspend fun addPlant(addPlant: AddPlant): AddPlantResult
    suspend fun editPlant(addPlant: AddPlant): EditPlantResult
    suspend fun deletePlant(plantId: Int): DelPlantResult
}