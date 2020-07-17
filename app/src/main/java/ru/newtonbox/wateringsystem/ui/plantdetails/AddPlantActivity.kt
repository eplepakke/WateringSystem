package ru.newtonbox.wateringsystem.ui.plantdetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_plant.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.newtonbox.wateringsystem.R
import ru.newtonbox.wateringsystem.WateringApplication
import ru.newtonbox.wateringsystem.model.AddPlant
import ru.newtonbox.wateringsystem.service.PlantService
import ru.newtonbox.wateringsystem.ui.plantlist.PlantListAdapter


class AddPlantActivity : AppCompatActivity() {
    private lateinit var service: PlantService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)
        service = (application as WateringApplication).service

        title = "Добавление"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.add_plant, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        Log.v("CHECK_CHECK", "tk tk $id")
        if (id == android.R.id.home) {
            finish()
            return true
        }
        else if(id == R.id.action_add) {
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
        val serverPump = add_server_pump.text.toString()
        val minSoil = add_min_soil.text.toString().toDouble()
        val time = add_watering_time.text.toString().toInt()
        val plant = AddPlant(0,
            name,
            waterLevel,
            soilMoisture,
            pumpState,
            serverPump,
            minSoil,
            time)
        GlobalScope.launch(Dispatchers.IO) {
            val res = service.addPlant(plant)
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