package com.aswingt.flightsearch.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class, FavoriteFlight::class], version = 1, exportSchema = false)
abstract class FlightsDatabase : RoomDatabase() {

    abstract val flightsDao: FlightsDao

    companion object {
        @Volatile
        private var Instance: FlightsDatabase? = null

        fun getDatabase(context: Context): FlightsDatabase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(
                        context,
                        FlightsDatabase::class.java,
                        "flights_db"
                    )
                    .createFromAsset("database/flight_search.db")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}