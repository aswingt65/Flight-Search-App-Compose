package com.aswingt.flightsearch

import android.app.Application
import com.aswingt.flightsearch.di.AppContainer
import com.aswingt.flightsearch.di.DefaultAppContainer

class FlightSearchApplication : Application() {

    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer(this)
    }
}