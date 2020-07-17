package ru.newtonbox.wateringsystem

import android.app.Application
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.newtonbox.wateringsystem.mock.*
import ru.newtonbox.wateringsystem.service.PlantRetrofitService
import ru.newtonbox.wateringsystem.service.PlantService
import ru.newtonbox.wateringsystem.service.PlantServiceImpl


class WateringApplication : Application() {
    val service: PlantService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ms2.newtonbox.ru/")
            .client(client)
            .build()
        service = PlantServiceImpl(retrofit.create(PlantRetrofitService::class.java))
        /*service = PlantServiceImpl(object : PlantRetrofitService {
            override suspend fun getPlantList(): ResponseBody {
                return MOCK_PLANT_LIST.toResponseBody("application/json; charset=utf-8".toMediaType())
            }

            override suspend fun getPlantInfo(plantId: Int): ResponseBody {
                return MOCK_PLANT_INFO.toResponseBody("application/json; charset=utf-8".toMediaType())
            }

            override suspend fun sendWatering(plantId: Int): ResponseBody {
                return MOCK_WATERING.toResponseBody("application/json; charset=utf-8".toMediaType())
            }

            override suspend fun getSoilMoisture(plantId: Int): ResponseBody {
                return MOCK_SOIL_MOISTURE.toResponseBody("application/json; charset=utf-8".toMediaType())
            }

            override suspend fun getWaterLevel(plantId: Int): ResponseBody {
                return MOCK_WATER_LEVEL.toResponseBody("application/json; charset=utf-8".toMediaType())
            }

            override suspend fun getPumpState(plantId: Int): ResponseBody {
                return MOCK_PUMP_STATE.toResponseBody("application/json; charset=utf-8".toMediaType())
            }
        })*/
    }
}