package com.aswingt.flightsearch.ui.screens

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aswingt.flightsearch.FlightSearchApplication
import com.aswingt.flightsearch.ui.screens.flights.FlightsViewModel
import com.aswingt.flightsearch.ui.screens.main.MainViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for MainViewModel
        initializer {
            MainViewModel(
                application().appContainer.appRepository
            )
        }

        // Initializer for FlightsViewModel
        initializer {
            FlightsViewModel(
                this.createSavedStateHandle(),
                application().appContainer.appRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [FlightSearchApplication].
 */
fun CreationExtras.application(): FlightSearchApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)