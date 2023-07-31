package com.aswingt.flightsearch.ui.screens.flights

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aswingt.flightsearch.data.local.SaveableFlightProvider
import com.aswingt.flightsearch.data.local.room.FavoriteFlight
import com.aswingt.flightsearch.data.local.room.SaveableFlight
import com.aswingt.flightsearch.data.repository.AppRepository
import com.aswingt.flightsearch.ui.navigation.Route
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlightsViewModel(
    savedStateHandle: SavedStateHandle,
    private val appRepository: AppRepository
) : ViewModel() {

    val airportCode: String = savedStateHandle[Route.Flights.A1_STRING_AIRPORT_ID] ?: ""

    private val saveableFlights = MutableStateFlow(emptyList<SaveableFlight>())
    private val favoritesFlights = appRepository.getFavoriteFlightsStream()
    private val combinedData: Flow<List<SaveableFlight>> =
        combine(
            saveableFlights,
            favoritesFlights
        ) { flights, favorites ->
            flights.map { flight ->
                val isFavorite = favorites.find { it.id == flight.id } != null
                flight.copy(isFavorite = isFavorite)
            }
        }

    val flights: StateFlow<List<SaveableFlight>> =
        combinedData.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        saveableFlights.value = SaveableFlightProvider.saveableFlights[airportCode] ?: emptyList()
    }

    fun onFavoriteClick(flight: FavoriteFlight) {
        viewModelScope.launch {
            val found = appRepository.getFavoriteFlight(flight.id)
            if (found == null) {
                appRepository.saveFavoriteFlight(flight)
            } else {
                appRepository.deleteFavoriteFlight(flight)
            }
        }
    }
}