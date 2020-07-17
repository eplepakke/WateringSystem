package ru.newtonbox.wateringsystem.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.newtonbox.wateringsystem.R
import ru.newtonbox.wateringsystem.model.SoilMoisture

class SoilMoistureListAdapter(private val soilMoistureSet: List<SoilMoisture>) :
        RecyclerView.Adapter<SoilMoistureListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.soil_moisture_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = soilMoistureSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val soilMoisture = soilMoistureSet[position]
        holder.soilMoisture = soilMoisture
        holder.textView.text = soilMoisture.time
        holder.valueView.text = soilMoisture.value.toString()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var soilMoisture: SoilMoisture? = null
        val valueView: TextView
        val textView: TextView

        init {
            valueView = v.findViewById(R.id.soil_moisture_card_status)
            textView = v.findViewById(R.id.soil_moisture_card_date)
        }
    }
}