package com.aswingt.flightsearch.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aswingt.flightsearch.R
import com.aswingt.flightsearch.data.local.room.Airport

@Composable
fun SuggestionsList(
    suggestions: List<Airport>,
    onSuggestionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (suggestions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(R.string.no_results))
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(suggestions, key = { it.id }) {
                SuggestionItem(suggestion = it, onClick = onSuggestionClick)
            }
        }
    }
}