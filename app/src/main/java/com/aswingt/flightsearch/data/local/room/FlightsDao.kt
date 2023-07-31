package com.aswingt.flightsearch.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightsDao {

    @Query(
        "SELECT * FROM airport " +
                "WHERE iata_code LIKE '%' || :query || '%' OR name LIKE '%' || :query || '%'" +
                "ORDER BY passengers DESC"
    )
    suspend fun getAirportSuggestions(query: String): List<Airport>

    @Query("SELECT * FROM airport ORDER BY passengers DESC")
    suspend fun getAirports(): List<Airport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteFlight(flight: FavoriteFlight)

    @Delete
    suspend fun deleteFavoriteFlight(flight: FavoriteFlight)

    @Query("SELECT * FROM favorite")
    fun getFavoriteFlightsStream(): Flow<List<FavoriteFlight>>

    @Query("SELECT * FROM favorite WHERE id = :id")
    suspend fun getFavoriteFlight(id: Int): FavoriteFlight?
}