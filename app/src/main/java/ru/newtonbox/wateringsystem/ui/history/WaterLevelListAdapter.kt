package ru.newtonbox.wateringsystem.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.newtonbox.wateringsystem.model.Plant
import ru.newtonbox.wateringsystem.R
import ru.newtonbox.wateringsystem.model.PumpState
import ru.newtonbox.wateringsystem.model.WaterLevel

class WaterLevelListAdapter(private val waterLevelSet: List<WaterLevel>) :
        RecyclerView.Adapter<WaterLevelListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.water_level_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = waterLevelSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val waterLevel = waterLevelSet[position]
        holder.pumpState = waterLevel
        holder.textView.text = waterLevel.time
        holder.imgView.setImageResource(
            if(waterLevel.value) R.drawable.ok
            else R.drawable.bad
        )
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var pumpState: WaterLevel? = null
        val imgView: ImageView
        val textView: TextView

        init {
            imgView = v.findViewById(R.id.water_level_card_status)
            textView = v.findViewById(R.id.water_level_card_date)
        }
    }
}