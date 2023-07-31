package com.aswingt.flightsearch.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aswingt.flightsearch.R
import com.aswingt.flightsearch.data.local.room.FavoriteFlight
import com.aswingt.flightsearch.data.local.room.SaveableFlight

@Composable
fun SaveableFlightItem(
    saveableFlight: SaveableFlight,
    onFavoriteClick: (FavoriteFlight) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(
                onClick = {
                    onFavoriteClick(
                        FavoriteFlight(
                            saveableFlight.id,
                            saveableFlight.departureCode,
                            saveableFlight.destinationCode
                        )
                    )
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = if (saveableFlight.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorite_icon_cont_desc)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = stringResource(R.string.depart),
                    style = MaterialTheme.typography.overline
                )
                Row {
                    Text(
                        text = saveableFlight.departureCode,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = saveableFlight.departureName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.arrive),
                    style = MaterialTheme.typography.overline
                )
                Row {
                    Text(
                        text = saveableFlight.destinationCode,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = saveableFlight.destinationName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}