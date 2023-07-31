package com.aswingt.flightsearch.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "iata_code")
    val code: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "passengers")
    val passengers: Int
)
