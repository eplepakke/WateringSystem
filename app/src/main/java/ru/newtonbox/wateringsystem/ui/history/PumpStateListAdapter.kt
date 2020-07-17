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

class PumpStateListAdapter(private val pumpStateSet: List<PumpState>) :
        RecyclerView.Adapter<PumpStateListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.pump_state_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = pumpStateSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pumpState = pumpStateSet[position]
        holder.pumpState = pumpState
        holder.textView.text = pumpState.time
        holder.imgView.setImageResource(
            if(pumpState.value) R.drawable.ok
            else R.drawable.bad
        )
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var pumpState: PumpState? = null
        val imgView: ImageView
        val textView: TextView

        init {
            imgView = v.findViewById(R.id.pump_state_card_status)
            textView = v.findViewById(R.id.pump_state_card_date)
        }
    }
}