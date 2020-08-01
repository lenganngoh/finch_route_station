package gohleng.apps.finch_route_station.main.ui

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import gohleng.apps.finch_route_station.R
import gohleng.apps.finch_route_station.api.Route
import gohleng.apps.finch_route_station.main.util.RouteOnItemClickListener
import net.cachapa.expandablelayout.ExpandableLayout
import java.lang.StringBuilder

class RouteListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val txtRouteName: TextView = view.findViewById(R.id.txtRouteName)
    private val txtRouteDetail: TextView = view.findViewById(R.id.txtRouteDetail)
    private val contExpandableContent: ExpandableLayout = view.findViewById(R.id.contExpandableContent)

    fun bind(route: Route, routeOnItemClickListener: RouteOnItemClickListener) {
        txtRouteName.text = route.name

        val stringBuilder = StringBuilder()
        for (item in route.stop_times) {
            stringBuilder.append(item.departure_time)
            stringBuilder.append("\t")
            stringBuilder.append(item.shape)
            stringBuilder.append("\n")
        }

        txtRouteDetail.text = stringBuilder.toString()

        itemView.setOnClickListener {
            routeOnItemClickListener.onItemClick(route, contExpandableContent)
        }
    }
}