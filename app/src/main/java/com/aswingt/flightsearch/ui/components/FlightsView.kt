package com.aswingt.flightsearch.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aswingt.flightsearch.data.local.room.FavoriteFlight
import com.aswingt.flightsearch.data.local.room.SaveableFlight

@Composable
fun FlightsView(
    title: String,
    list: List<SaveableFlight>,
    onFavoriteClick: (FavoriteFlight) -> Unit,
    modifier: Modifier = Modifier
) {
    if (list.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = title)
        }
    } else {
        Column(modifier = modifier.fillMaxSize()) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(list) {
                    SaveableFlightItem(saveableFlight = it, onFavoriteClick = onFavoriteClick)
                }
            }
        }
    }
}