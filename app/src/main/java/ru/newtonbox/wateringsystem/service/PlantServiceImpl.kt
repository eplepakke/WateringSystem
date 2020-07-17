package ru.newtonbox.wateringsystem.service

import org.json.JSONObject
import retrofit2.HttpException
import ru.newtonbox.wateringsystem.model.*
import java.text.SimpleDateFormat
import java.util.*

class PlantServiceImpl(private val retrofitService: PlantRetrofitService) : PlantService {
    override suspend fun getPlantList(): List<Plant> {
        try {
            val resp = retrofitService.getPlantList()
            val json = JSONObject(resp.string())
            val jsonPlants = json.optJSONArray("plantinfo_list") ?: return emptyList()
            val list = mutableListOf<Plant>()
            for(i in 0 until jsonPlants.length()) {
                val jsonPlant = jsonPlants.getJSONObject(i)
                list.add(
                    Plant(
                        jsonPlant.getInt("id"),
                        jsonPlant.getString("name"),
                        jsonPlant.getInt("watering_time"),
                        jsonPlant.getDouble("min_soil_moisture")))
            }
            return list
        } catch(e: HttpException) {
            return emptyList()
        }
    }

    override suspend fun getPlantInfo(plantId: Int): PlantInfo {
        try {
            val resp = retrofitService.getPlantInfo(plantId)
            val json = JSONObject(resp.string())
            val jsonInfo = json.optJSONArray("object_list")?:return PlantInfo(null,null,null,null,null,null,null,null,null)
            var waterLevel: Boolean? = null
            var waterDevice: String? = null
            var pumpState: String? = null
            var pumpDevice: String? = null
            var soilMoisture: Double? = null
            var soilDevice: String? = null
            for(i in 0 until jsonInfo.length()) {
                val something = jsonInfo.getJSONObject(i)
                when {
                    something.getString("channel_source").contains("water_level") -> {
                        waterLevel = something.getBoolean("value")
                        waterDevice = something.getString("channel_source")
                    }
                    something.getString("channel_source").contains("soil_moisture") -> {
                        soilMoisture = something.getDouble("value")
                        soilDevice = something.getString("channel_source")
                    }
                    something.getString("channel_source").contains("pump_state") -> {
                        pumpState = isoToShortDate(something.getString("time_received").substringBefore("."))
                        pumpDevice = something.getString("channel_source")
                    }
                }
            }
            val plantinfo = json.getJSONObject("info")
            return PlantInfo(plantinfo.getString("name"),plantinfo.getDouble("min_soil_moisture"),plantinfo.getInt("watering_time"),waterDevice,soilDevice,pumpDevice,waterLevel, soilMoisture, pumpState)
        } catch(e: HttpException) {
            return PlantInfo(null,null,null,null,null,null,null,null,null)
        }
    }

    override suspend fun sendWatering(plantId: Int): WateringResult {
        try {
            val resp = retrofitService.sendWatering(plantId)
            val json = JSONObject(resp.string())
            return WateringResult(json.getString("Response"))
        } catch(e: HttpException) {
            return WateringResult("Network Error")
        }
    }

    override suspend fun getSoilMoisture(plantId: Int): List<SoilMoisture> {
        try {
            val resp = retrofitService.getSoilMoisture(plantId)
            val json = JSONObject(resp.string())
            val jsonSoils = json.optJSONArray("soilmoisture_list") ?: return emptyList()
            val list = mutableListOf<SoilMoisture>()
            for (i in 0 until jsonSoils.length()) {
                val jsonSoil = jsonSoils.getJSONObject(i)
                val time = jsonSoil.getString("time_received")
                list.add(
                    SoilMoisture(
                        jsonSoil.getInt("id"),
                        jsonSoil.getDouble("value"),
                        isoToShortDate2(time.substringBefore("."))
                    )
                )
            }
            return list
        } catch (e: HttpException) {
            return emptyList()
        }
    }

    override suspend fun getWaterLevel(plantId: Int): List<WaterLevel> {
        try {
            val resp = retrofitService.getWaterLevel(plantId)
            val json = JSONObject(resp.string())
            val jsonSoils = json.optJSONArray("waterlevel_list") ?: return emptyList()
            val list = mutableListOf<WaterLevel>()
            for(i in 0 until jsonSoils.length()) {
                val jsonSoil = jsonSoils.getJSONObject(i)
                list.add(WaterLevel(jsonSoil.getInt("id"),
                    jsonSoil.getBoolean("value"),
                    isoToShortDate2(jsonSoil.getString("time_received").substringBefore("."))))
            }
            return list
        } catch (e: HttpException) {
            return emptyList()
        }
    }

    override suspend fun getPumpState(plantId: Int): List<PumpState> {
        try {
            val resp = retrofitService.getPumpState(plantId)
            val json = JSONObject(resp.string())
            val jsonSoils = json.optJSONArray("pumpstate_list") ?: return emptyList()
            val list = mutableListOf<PumpState>()
            for(i in 0 until jsonSoils.length()) {
                val jsonSoil = jsonSoils.getJSONObject(i)
                list.add(PumpState(jsonSoil.getInt("id"),
                    jsonSoil.getBoolean("value"),
                    isoToShortDate2(jsonSoil.getString("time_received").substringBefore("."))))
            }
            return list
        } catch (e: HttpException) {
            return emptyList()
        }
    }

    override suspend fun addPlant(addPlant: AddPlant): AddPlantResult {
        try {
            val resp =
                retrofitService.addPlant(
                    addPlant.name,
                    addPlant.waterLevel,
                    addPlant.soilMoisture,
                    addPlant.pumpState,
                    addPlant.serverPump,
                    addPlant.minSoil,
                    addPlant.time)
            val json = JSONObject(resp.string())
            return AddPlantResult(json.getString("Response"))
        } catch (e: HttpException) {
            return AddPlantResult("Ошибка")
        }
    }

    override suspend fun editPlant(addPlant: AddPlant): EditPlantResult {
        try {
            val resp =
                retrofitService.editPlant(
                    addPlant.id,
                    addPlant.name,
                    addPlant.waterLevel,
                    addPlant.soilMoisture,
                    addPlant.pumpState,
                    addPlant.minSoil,
                    addPlant.time)
            val json = JSONObject(resp.string())
            return EditPlantResult(json.getString("Response"))
        } catch (e: HttpException) {
            return EditPlantResult("Ошибка")
        }
    }

    override suspend fun deletePlant(plantId: Int): DelPlantResult {
        try {
            val resp =
                retrofitService.deletePlant(plantId)
            val json = JSONObject(resp.string())
            return DelPlantResult(json.getString("Response"))
        } catch (e: HttpException) {
            return DelPlantResult("Ошибка")
        }
    }

    companion object {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val input2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val output = SimpleDateFormat("HH:mm:ss dd.MM")
        fun isoToShortDate(date: String): String {
            val obj = input.parse(date)
            return output.format(obj)
        }
        fun isoToShortDate2(date: String): String {
            val obj = input2.parse(date)
            return output.format(obj)
        }
    }
}