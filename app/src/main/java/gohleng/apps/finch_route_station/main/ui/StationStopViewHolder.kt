package gohleng.apps.finch_route_station.main.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import gohleng.apps.finch_route_station.R
import gohleng.apps.finch_route_station.api.Stop
import gohleng.apps.finch_route_station.main.util.StationStopOnItemClickListener
import gohleng.apps.finch_route_station.test.Test

class StationStopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val txtName: TextView = view.findViewById(R.id.txtName)
    private val txtAgency: TextView = view.findViewById(R.id.txtAgency)
    private val imgStation: ImageView = view.findViewById(R.id.imgStation)

    fun bind(stop: Stop, stationStopOnItemClickListener: StationStopOnItemClickListener) {
        txtName.text = stop.name
        txtAgency.text = stop.agency
        if (imgStation.drawable == null) imgStation.setImageResource(Test.getRandomDrawable())

        imgStation.setOnClickListener {
            stationStopOnItemClickListener.onItemClick(stop, itemView, imgStation.drawable.toBitmap())
        }
    }
}