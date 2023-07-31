package com.aswingt.flightsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aswingt.flightsearch.ui.navigation.FlightSearchApp
import com.aswingt.flightsearch.ui.theme.FlightSearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlightSearchTheme {
                FlightSearchApp()
            }
        }
    }
}