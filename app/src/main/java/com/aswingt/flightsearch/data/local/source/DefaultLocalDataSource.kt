package com.aswingt.flightsearch.data.local.source

import com.aswingt.flightsearch.data.local.room.Airport
import com.aswingt.flightsearch.data.local.datastore.LocalSearchDataSource
import com.aswingt.flightsearch.data.local.room.FavoriteFlight
import com.aswingt.flightsearch.data.local.room.FlightsDao
import kotlinx.coroutines.flow.Flow

class DefaultLocalDataSource(
    private val localSearchDataSource: LocalSearchDataSource,
    private val localFlightsDataSource: FlightsDao
) : LocalDataSource {

    override suspend fun saveSearchQuery(query: String): Boolean {
        return localSearchDataSource.saveSearchQuery(query)
    }

    override fun getSearchQueryStream(): Flow<String> {
        return localSearchDataSource.getSearchQueryStream()
    }

    override suspend fun getAirportSuggestions(query: String): List<Airport> {
        return localFlightsDataSource.getAirportSuggestions(query)
    }

    override suspend fun getAirports(): List<Airport> {
        return localFlightsDataSource.getAirports()
    }

    override suspend fun saveFavoriteFlight(flight: FavoriteFlight) {
        return localFlightsDataSource.saveFavoriteFlight(flight)
    }

    override suspend fun deleteFavoriteFlight(flight: FavoriteFlight) {
        return localFlightsDataSource.deleteFavoriteFlight(flight)
    }

    override fun getFavoriteFlightsStream(): Flow<List<FavoriteFlight>> {
        return localFlightsDataSource.getFavoriteFlightsStream()
    }

    override suspend fun getFavoriteFlight(id: Int): FavoriteFlight? {
        return localFlightsDataSource.getFavoriteFlight(id)
    }
}