package ru.newtonbox.wateringsystem.ui.plantlist

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_plant.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.newtonbox.wateringsystem.R
import ru.newtonbox.wateringsystem.WateringApplication
import ru.newtonbox.wateringsystem.model.Plant
import ru.newtonbox.wateringsystem.service.PlantService
import ru.newtonbox.wateringsystem.ui.plantdetails.AddPlantActivity
import ru.newtonbox.wateringsystem.ui.plantdetails.PlantInfoActivity


class MainActivity : AppCompatActivity() {
    private lateinit var service: PlantService

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        service = (application as WateringApplication).service

        title = "Список цветов"

        recyclerView = plant_list
        setRecyclerViewLayoutManager()

        add_plant.setOnClickListener {
            addPlant()
        }

        GlobalScope.launch(Dispatchers.IO) {
            val res = service.getPlantList()
            launch(Dispatchers.Main) {
                recyclerView.adapter = PlantListAdapter(res) {
                    showPlantInfo(it)
                }
            }
        }
    }

    private fun addPlant() {
        val intent = Intent(this, AddPlantActivity::class.java)
        startActivityForResult(intent, 6667)
    }

    private fun showPlantInfo(plant: Plant) {
        val intent = Intent(this, PlantInfoActivity::class.java)
        intent.putExtra("plant_id", plant.id)
        intent.putExtra("plant_name", plant.name)
        intent.putExtra("watering_time", plant.wateringTime)
        intent.putExtra("min_soil_moisture", plant.minSoilMoisture)
        startActivity(intent)
    }

    private fun setRecyclerViewLayoutManager() {
        var scrollPosition = 0
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }
        val lm = LinearLayoutManager(this)
        recyclerView.layoutManager = lm
        recyclerView.scrollToPosition(scrollPosition)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        GlobalScope.launch(Dispatchers.IO) {
            val res = service.getPlantList()
            launch(Dispatchers.Main) {
                recyclerView.adapter = PlantListAdapter(res) {
                    showPlantInfo(it)
                }
            }
        }
    }
}