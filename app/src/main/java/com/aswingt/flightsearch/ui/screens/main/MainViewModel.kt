package com.aswingt.flightsearch.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aswingt.flightsearch.data.local.AirportProvider
import com.aswingt.flightsearch.data.local.SaveableFlightProvider
import com.aswingt.flightsearch.data.local.room.FavoriteFlight
import com.aswingt.flightsearch.data.local.room.SaveableFlight
import com.aswingt.flightsearch.data.repository.AppRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    private val _favorites = appRepository.getFavoriteFlightsStream()

    val uiState: StateFlow<MainUiState> =
        combine(
            _uiState,
            _favorites
        ) { mainUiState, favorites ->
            mainUiState.copy(favorites = mapToSaveable(favorites))
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            MainUiState()
        )

    init {
        viewModelScope.launch {
            val list = appRepository.getAirports()
            AirportProvider.cacheAirports(list)
            val query = appRepository.getSearchQueryStream().first()
            if (query.isNotEmpty()) processSearchQuery(query)
            else _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun mapToSaveable(favorites: List<FavoriteFlight>): List<SaveableFlight> {
        return favorites.map { favorite ->
            val departureName =
                SaveableFlightProvider.saveableFlights[favorite.departureCode]?.first()?.departureName
                    ?: ""
            val destinationName =
                SaveableFlightProvider.saveableFlights[favorite.destinationCode]?.first()?.departureName
                    ?: ""
            favorite.toSaveableFlight(departureName, destinationName)
        }
    }

    private var job: Job? = null
    fun onQueryChange(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            appRepository.saveSearchQuery(query)
        }
        processSearchQuery(query)
    }

    private fun processSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        if (query.isEmpty()) return
        viewModelScope.launch {
            val suggestions = appRepository.getAirportSuggestions(query)
            _uiState.update {
                it.copy(
                    suggestions = suggestions,
                    isLoading = false
                )
            }
        }
    }

    fun onFavoriteClick(flight: FavoriteFlight) {
        viewModelScope.launch {
            appRepository.deleteFavoriteFlight(flight)
        }
    }
}