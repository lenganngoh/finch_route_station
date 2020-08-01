package gohleng.apps.finch_route_station.api

data class Station(
    val time: Long,
    val stops: List<Stop>,
    val uri: String,
    val name: String
)

data class Stop(
    val routes: List<Route>,
    val name: String,
    val uri: String,
    val agency: String
)

data class Route(
    val name: String,
    val route_group_id: String,
    val stop_times: List<StopTime>
)

data class StopTime(
    val departure_time: String,
    val shape: String,
    val departure_timestamp: String,
    val service_id: Int
)