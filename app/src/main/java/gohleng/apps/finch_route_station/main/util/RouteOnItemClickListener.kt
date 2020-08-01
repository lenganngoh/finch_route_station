package gohleng.apps.finch_route_station.main.util

import gohleng.apps.finch_route_station.api.Route
import net.cachapa.expandablelayout.ExpandableLayout

class RouteOnItemClickListener(private val onItemClickListener: (Route, ExpandableLayout) -> Unit) {
    fun onItemClick(route: Route, view: ExpandableLayout) {
        onItemClickListener(route, view)
    }
}