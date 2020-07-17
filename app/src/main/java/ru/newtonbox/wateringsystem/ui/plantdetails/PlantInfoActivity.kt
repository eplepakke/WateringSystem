package ru.newtonbox.wateringsystem.ui.plantdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_plant_info.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.newtonbox.wateringsystem.R
import ru.newtonbox.wateringsystem.WateringApplication
import ru.newtonbox.wateringsystem.model.PlantInfo
import ru.newtonbox.wateringsystem.service.PlantService
import ru.newtonbox.wateringsystem.ui.history.PumpStateDetailsActivity
import ru.newtonbox.wateringsystem.ui.history.SoilMoistureDetailsActivity
import ru.newtonbox.wateringsystem.ui.history.WaterLevelDetailsActivity

class PlantInfoActivity : AppCompatActivity() {
    private lateinit var service: PlantService
    var plantId: Int = 0
    var plantName: String = ""
    var plantInfo: PlantInfo = PlantInfo(null,null,null,null,null,null,null,null,null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_info)
        service = (application as WateringApplication).service

        plantId = intent.getIntExtra("plant_id", 0)
        plantName = intent.getStringExtra("plant_name")?:""
        plant_info_title.text = plantName

        title = "Информация о цветке"

        plant_info_pump_state.setOnClickListener(::pumpStateDetails)
        plant_info_soil_moisture.setOnClickListener(::soilMoistureDetails)
        plant_info_water_level.setOnClickListener(::waterLevelDetails)
        plant_info_watering.setOnClickListener(::watering)

        GlobalScope.launch(Dispatchers.IO) {
            plantInfo = service.getPlantInfo(plantId)
            launch(Dispatchers.Main) {
                updatePlantInfo(plantInfo)
            }
        }
    }

    private fun pumpStateDetails(view: View) {
        val intent = Intent(this, PumpStateDetailsActivity::class.java)
        intent.putExtra("plant_id", plantId)
        intent.putExtra("plant_name", plantName)
        startActivity(intent)
    }

    private fun soilMoistureDetails(view: View) {
        val intent = Intent(this, SoilMoistureDetailsActivity::class.java)
        intent.putExtra("plant_id", plantId)
        intent.putExtra("plant_name", plantName)
        startActivity(intent)
    }

    private fun waterLevelDetails(view: View) {
        val intent = Intent(this, WaterLevelDetailsActivity::class.java)
        intent.putExtra("plant_id", plantId)
        intent.putExtra("plant_name", plantName)
        startActivity(intent)
    }

    private fun watering(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val res = service.sendWatering(plantId)
            launch(Dispatchers.Main) {
                Toast.makeText(this@PlantInfoActivity, res.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updatePlantInfo(plantInfo: PlantInfo) {
        when(plantInfo.pumpState) {
            null -> pump_state_status.text = "Ошибка"
            else -> pump_state_status.text = plantInfo.pumpState
        }
        when(plantInfo.waterLevel) {
            null -> water_level_status.setImageResource(R.drawable.not_loaded)
            true -> water_level_status.setImageResource(R.drawable.ok)
            false -> water_level_status.setImageResource(R.drawable.bad)
        }
        when(plantInfo.soilMoisture) {
            null -> soil_moisture_status.text = "Ошибка"
            else -> soil_moisture_status.text = plantInfo.soilMoisture.toString()
        }
        if(plantInfo.watering!=null&&plantInfo.watering) {
            soil_moisture_status.setTextColor(resources.getColor(R.color.soil_moisture_bad))
        }
        else {
            soil_moisture_status.setTextColor(resources.getColor(R.color.soil_moisture_status))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.edit_plant, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        Log.v("CHECK_CHECK", "tk tk $id")
        if (id == android.R.id.home) {
            finish()
            return true
        }
        else if(id == R.id.action_edit) {
            editPlant()
            return true
        }
        else if(id == R.id.action_delete) {
            delPlant()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun delPlant() {
        GlobalScope.launch(Dispatchers.IO) {
            val res = service.deletePlant(plantId)
            launch(Dispatchers.Main) {
                Toast.makeText(this@PlantInfoActivity, res.message, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun editPlant() {
        val intent = Intent(this, EditPlantActivity::class.java)
        Log.v("CHECK_CHECK", "check: ${plantInfo.name}")
        intent.putExtra("plant_id",plantId)
        intent.putExtra("name", plantInfo.name)
        intent.putExtra("time", plantInfo.wateringDuration)
        intent.putExtra("min_soil", plantInfo.minSoil)
        intent.putExtra("water_level", plantInfo.waterDevice)
        intent.putExtra("soil_moisture", plantInfo.soilDevice)
        intent.putExtra("pump_state", plantInfo.pumpDevice)
        startActivityForResult(intent, 6667)
    }
}