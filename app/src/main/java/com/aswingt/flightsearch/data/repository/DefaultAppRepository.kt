package com.aswingt.flightsearch.data.repository

import com.aswingt.flightsearch.data.local.room.Airport
import com.aswingt.flightsearch.data.local.room.FavoriteFlight
import com.aswingt.flightsearch.data.local.source.LocalDataSource
import kotlinx.coroutines.flow.Flow

class DefaultAppRepository(private val localDataSource: LocalDataSource) : AppRepository {

    override suspend fun saveSearchQuery(query: String): Boolean =
        localDataSource.saveSearchQuery(query)

    override fun getSearchQueryStream(): Flow<String> = localDataSource.getSearchQueryStream()

    override suspend fun getAirportSuggestions(query: String): List<Airport> =
        localDataSource.getAirportSuggestions(query)

    override suspend fun getAirports(): List<Airport> =
        localDataSource.getAirports()

    override suspend fun saveFavoriteFlight(flight: FavoriteFlight) =
        localDataSource.saveFavoriteFlight(flight)

    override suspend fun deleteFavoriteFlight(flight: FavoriteFlight) =
        localDataSource.deleteFavoriteFlight(flight)

    override fun getFavoriteFlightsStream(): Flow<List<FavoriteFlight>> =
        localDataSource.getFavoriteFlightsStream()

    override suspend fun getFavoriteFlight(id: Int): FavoriteFlight? =
        localDataSource.getFavoriteFlight(id)
}