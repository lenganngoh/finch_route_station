package gohleng.apps.finch_route_station.main.util

import android.graphics.Bitmap
import android.view.View
import gohleng.apps.finch_route_station.api.Stop

class StationStopOnItemClickListener(
    private val onItemClickListener: (Stop, View, Bitmap) -> Unit) {

    fun onItemClick(stop: Stop, view: View, bitmap: Bitmap) {
        onItemClickListener(stop, view, bitmap)
    }
}