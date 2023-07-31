package com.aswingt.flightsearch.di

import com.aswingt.flightsearch.data.repository.AppRepository

interface AppContainer {

    val appRepository: AppRepository
}