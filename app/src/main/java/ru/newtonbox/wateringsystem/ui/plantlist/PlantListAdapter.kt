package ru.newtonbox.wateringsystem.ui.plantlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.newtonbox.wateringsystem.model.Plant
import ru.newtonbox.wateringsystem.R

class PlantListAdapter(private val plantSet: List<Plant>,
                       private val onClick : (Plant)->Unit) :
        RecyclerView.Adapter<PlantListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_card, parent, false)
        return ViewHolder(
            v,
            onClick
        )
    }

    override fun getItemCount() = plantSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = plantSet[position]
        holder.plant = plant
        holder.textView.text = plant.name
    }

    class ViewHolder(v: View, onClick: (Plant) -> Unit) : RecyclerView.ViewHolder(v) {
        var plant: Plant? = null
        val imgView: ImageView
        val textView: TextView

        init {
            v.setOnClickListener { plant?.let { onClick(it) } }
            imgView = v.findViewById(R.id.plant_card_img)
            textView = v.findViewById(R.id.plant_card_name)
        }
    }
}