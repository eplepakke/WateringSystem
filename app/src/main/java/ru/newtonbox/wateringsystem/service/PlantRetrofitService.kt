package ru.newtonbox.wateringsystem.service

import okhttp3.ResponseBody
import retrofit2.http.*

interface PlantRetrofitService {
    @GET("list/")
    suspend fun getPlantList(): ResponseBody
    @GET("get/plant={plantId}")
    suspend fun getPlantInfo(@Path("plantId") plantId: Int): ResponseBody
    @GET("send/pump_on/plant={plantId}")
    suspend fun sendWatering(@Path("plantId") plantId: Int): ResponseBody
    @GET("get/plant={plantId}/soil_moisture")
    suspend fun getSoilMoisture(@Path("plantId") plantId: Int): ResponseBody
    @GET("get/plant={plantId}/water_level")
    suspend fun getWaterLevel(@Path("plantId") plantId: Int): ResponseBody
    @GET("get/plant={plantId}/pump_state")
    suspend fun getPumpState(@Path("plantId") plantId: Int): ResponseBody
    @FormUrlEncoded
    @POST("add/plant")
    suspend fun addPlant(@Field("name_plant")
                             name: String,
                             @Field("water_level")
                             waterLevel: String,
                             @Field("soil_moisture")
                             soilMoisture: String,
                             @Field("pump_state")
                             pumpState: String,
                             @Field("server_pump")
                             serverPump: String,
                             @Field("min_soil_moisture")
                             minSoil: Double,
                             @Field("watering_time")
                             wateringTime: Int): ResponseBody
    @FormUrlEncoded
    @POST("change/plant={plantId}")
    suspend fun editPlant(@Path("plantId") plantId: Int,
                          @Field("name_planet")
                          name: String?,
                          @Field("water_level")
                          waterLevel: String?,
                          @Field("soil_moisture")
                          soilMoisture: String?,
                          @Field("pump_state")
                          pumpState: String?,
                          @Field("min_soil_moisture")
                          minSoil: Double?,
                          @Field("watering_time")
                          wateringTime: Int?): ResponseBody
    @GET("delete/plant={plantId}")
    suspend fun deletePlant(@Path("plantId") plantId: Int): ResponseBody
}