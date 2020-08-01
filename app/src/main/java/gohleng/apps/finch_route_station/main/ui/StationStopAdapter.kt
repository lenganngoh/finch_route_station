package gohleng.apps.finch_route_station.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gohleng.apps.finch_route_station.R
import gohleng.apps.finch_route_station.api.Stop
import gohleng.apps.finch_route_station.main.util.StationStopOnItemClickListener

class StationStopAdapter(
    private val stops: List<Stop>,
    private val itemClickListenerStationStop: StationStopOnItemClickListener
) : RecyclerView.Adapter<StationStopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationStopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_station_stop, parent, false)
        return StationStopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stops.size
    }

    override fun onBindViewHolder(holder: StationStopViewHolder, position: Int) {
        return holder.bind(stops[position], itemClickListenerStationStop)
    }
}