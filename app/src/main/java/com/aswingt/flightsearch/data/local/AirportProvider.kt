package com.aswingt.flightsearch.data.local

import com.aswingt.flightsearch.data.local.room.Airport

object AirportProvider {

    var list = listOf<Airport>()
        private set

    fun cacheAirports(list: List<Airport>) {
        this.list = list
    }
}