package com.aswingt.flightsearch.data.local.room

data class SaveableFlight(
    val id: Int,
    val departureCode: String,
    val departureName: String,
    val destinationCode: String,
    val destinationName: String,
    val isFavorite: Boolean
)
