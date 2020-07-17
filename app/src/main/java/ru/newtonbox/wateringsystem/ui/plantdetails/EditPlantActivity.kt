package ru.newtonbox.wateringsystem.ui.plantdetails

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_plant.*
import kotlinx.android.synthetic.main.activity_add_plant.add_min_soil
import kotlinx.android.synthetic.main.activity_add_plant.add_plant_name
import kotlinx.android.synthetic.main.activity_add_plant.add_pump_state
import kotlinx.android.synthetic.main.activity_add_plant.add_server_pump
import kotlinx.android.synthetic.main.activity_add_plant.add_soil_moisture
import kotlinx.android.synthetic.main.activity_add_plant.add_water_level
import kotlinx.android.synthetic.main.activity_add_plant.add_watering_time
import kotlinx.android.synthetic.main.activity_edit_plant.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.newtonbox.wateringsystem.R
import ru.newtonbox.wateringsystem.WateringApplication
import ru.newtonbox.wateringsystem.model.AddPlant
import ru.newtonbox.wateringsystem.service.PlantService

class EditPlantActivity : AppCompatActivity() {
    private lateinit var service: PlantService
    var plantId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_plant)
        service = (application as WateringApplication).service

        title = "Изменение"

        plantId = intent.getIntExtra("plant_id",0)
        add_plant_name.setText(intent.getStringExtra("name"))
        add_water_level.setText(intent.getStringExtra("water_level"))
        add_soil_moisture.setText(intent.getStringExtra("soil_moisture"))
        add_pump_state.setText(intent.getStringExtra("pump_state"))
        add_min_soil.setText(intent.getDoubleExtra("min_soil",0.0).toString())
        add_watering_time.setText(intent.getIntExtra("time", 0).toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.save_plant, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        else if(id == R.id.action_save) {
            sendAndClose()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun sendAndClose() {
        val name = add_plant_name.text.toString()
        val waterLevel = add_water_level.text.toString()
        val soilMoisture = add_soil_moisture.text.toString()
        val pumpState = add_pump_state.text.toString()
        val minSoil = add_min_soil.text.toString().toDouble()
        val time = add_watering_time.text.toString().toInt()
        val plant = AddPlant(plantId,
            name,
            waterLevel,
            soilMoisture,
            pumpState,
            "",
            minSoil,
            time)
        GlobalScope.launch(Dispatchers.IO) {
            val res = service.editPlant(plant)
            launch(Dispatchers.Main) {
                if(res.message == "OK") {
                    setResultAndQuit(plant)
                }
                else {
                    showError(res.message)
                }
            }
        }
    }

    private fun showError(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun setResultAndQuit(addPlant: AddPlant) {
        val intent = Intent()
        intent.putExtra("name", addPlant.name)
        intent.putExtra("waterLevel", addPlant.waterLevel)
        intent.putExtra("soilMoisture", addPlant.soilMoisture)
        intent.putExtra("pumpState", addPlant.pumpState)
        intent.putExtra("serverPump", addPlant.serverPump)
        intent.putExtra("minSoil", addPlant.minSoil)
        intent.putExtra("time", addPlant.time)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}