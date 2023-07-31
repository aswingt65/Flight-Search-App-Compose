package com.aswingt.flightsearch.ui.navigation

object Route {

    const val MAIN = "main"

    object Flights {
        private const val default = "flights"
        const val A1_STRING_AIRPORT_ID = "airportCode"
        const val ROUTE = "$default/{$A1_STRING_AIRPORT_ID}"
        fun createRoute(airportCode: String): String {
            return "$default/$airportCode"
        }
    }
}