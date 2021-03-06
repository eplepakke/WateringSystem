package ru.newtonbox.wateringsystem.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_soil_moisture_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.newtonbox.wateringsystem.R
import ru.newtonbox.wateringsystem.WateringApplication
import ru.newtonbox.wateringsystem.service.PlantService

class SoilMoistureDetailsActivity : AppCompatActivity() {
    private lateinit var service: PlantService

    var plantId: Int = 0
    var plantName: String = ""

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soil_moisture_details)
        service = (application as WateringApplication).service

        plantId = intent.getIntExtra("plant_id", 0)
        plantName = intent.getStringExtra("plant_name")?:""

        title = "$plantName и влажность"

        recyclerView = soil_moisture_list
        setRecyclerViewLayoutManager()

        GlobalScope.launch(Dispatchers.IO) {
            val res = service.getSoilMoisture(plantId)
            launch(Dispatchers.Main) {
                recyclerView.adapter = SoilMoistureListAdapter(res)
            }
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}