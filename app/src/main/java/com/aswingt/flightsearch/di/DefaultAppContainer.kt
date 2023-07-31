package com.aswingt.flightsearch.di

import android.content.Context
import com.aswingt.flightsearch.data.local.datastore.LocalSearchDataSource
import com.aswingt.flightsearch.data.local.source.LocalDataSource
import com.aswingt.flightsearch.data.local.datastore.PreferencesDataStore
import com.aswingt.flightsearch.data.local.datastore.dataStore
import com.aswingt.flightsearch.data.local.room.FlightsDao
import com.aswingt.flightsearch.data.local.room.FlightsDatabase
import com.aswingt.flightsearch.data.local.source.DefaultLocalDataSource
import com.aswingt.flightsearch.data.repository.AppRepository
import com.aswingt.flightsearch.data.repository.DefaultAppRepository

class DefaultAppContainer(private val context: Context) : AppContainer {

    private val searchDataSource: LocalSearchDataSource by lazy {
        PreferencesDataStore(context.dataStore)
    }

    private val flightsDataSource: FlightsDao by lazy {
        FlightsDatabase.getDatabase(context).flightsDao
    }

    private val localDataSource: LocalDataSource by lazy {
        DefaultLocalDataSource(
            searchDataSource,
            flightsDataSource
        )
    }

    override val appRepository: AppRepository by lazy {
        DefaultAppRepository(localDataSource)
    }
}