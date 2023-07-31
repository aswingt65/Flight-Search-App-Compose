package com.aswingt.flightsearch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aswingt.flightsearch.data.local.room.Airport

@Composable
fun SuggestionItem(
    suggestion: Airport,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick(suggestion.code) }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = suggestion.code,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = suggestion.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}