package com.aswingt.flightsearch.data.repository

import com.aswingt.flightsearch.data.local.room.Airport
import com.aswingt.flightsearch.data.local.room.FavoriteFlight
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun saveSearchQuery(query: String): Boolean

    fun getSearchQueryStream(): Flow<String>

    suspend fun getAirportSuggestions(query: String): List<Airport>

    suspend fun getAirports(): List<Airport>

    suspend fun saveFavoriteFlight(flight: FavoriteFlight)

    suspend fun deleteFavoriteFlight(flight: FavoriteFlight)

    fun getFavoriteFlightsStream(): Flow<List<FavoriteFlight>>

    suspend fun getFavoriteFlight(id: Int): FavoriteFlight?
}