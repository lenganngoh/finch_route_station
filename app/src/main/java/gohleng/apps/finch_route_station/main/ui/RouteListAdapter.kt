package gohleng.apps.finch_route_station.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gohleng.apps.finch_route_station.R
import gohleng.apps.finch_route_station.api.Route
import gohleng.apps.finch_route_station.main.util.RouteOnItemClickListener
import gohleng.apps.finch_route_station.main.util.StationStopOnItemClickListener

class RouteListAdapter(
    private val route: List<Route>,
    private val itemClickListenerRoute: RouteOnItemClickListener
) : RecyclerView.Adapter<RouteListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_route_list, parent, false)
        return RouteListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return route.size
    }

    override fun onBindViewHolder(holder: RouteListViewHolder, position: Int) {
        return holder.bind(route[position], itemClickListenerRoute)
    }
}