package com.aswingt.flightsearch.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aswingt.flightsearch.R
import com.aswingt.flightsearch.ui.components.FlightsView
import com.aswingt.flightsearch.ui.components.SuggestionsList
import com.aswingt.flightsearch.ui.screens.AppViewModelProvider

@Composable
fun MainScreen(
    navigateToFlights: (String) -> Unit,
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState().value
    val focusManager = LocalFocusManager.current
    val searchQuery = uiState.searchQuery
    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = viewModel::onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = stringResource(R.string.search))
                },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onSearch = { focusManager.clearFocus() }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                )
            )
            if (searchQuery.isEmpty()) {
                val favorites = uiState.favorites
                val stringRes =
                    if (favorites.isEmpty()) R.string.no_favorite_flights_yet else R.string.favorite_flights
                val title = stringResource(id = stringRes)
                FlightsView(
                    title = title,
                    list = favorites,
                    onFavoriteClick = viewModel::onFavoriteClick
                )
            } else {
                SuggestionsList(
                    suggestions = uiState.suggestions,
                    onSuggestionClick = navigateToFlights
                )
            }
        }
    }
}