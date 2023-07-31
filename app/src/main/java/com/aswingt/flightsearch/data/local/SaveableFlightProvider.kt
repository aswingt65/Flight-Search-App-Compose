package com.aswingt.flightsearch.data.local

import com.aswingt.flightsearch.data.local.room.SaveableFlight

object SaveableFlightProvider {

    val saveableFlights: Map<String, List<SaveableFlight>>
        get() =
            AirportProvider.list.groupBy { it.code }.mapValues { it.key.getSaveableFlights() }

}

fun String.getSaveableFlights(): List<SaveableFlight> {
    val mine = AirportProvider.list.filter { it.code == this }
    val notMine = AirportProvider.list.filterNot { it.code == this }
    val myAirport = mine.first()
    var countId = myAirport.id * notMine.size
    val list = notMine.map {
        SaveableFlight(
            id = countId++,
            departureCode = this,
            departureName = myAirport.name,
            destinationCode = it.code,
            destinationName = it.name,
            isFavorite = false
        )
    }
    return list
}