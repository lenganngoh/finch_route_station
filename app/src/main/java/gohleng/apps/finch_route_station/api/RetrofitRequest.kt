package gohleng.apps.finch_route_station.api

import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitRequest {

    @GET("/finch_station.json")
    fun getRouteDetail(): Observable<Station>

}