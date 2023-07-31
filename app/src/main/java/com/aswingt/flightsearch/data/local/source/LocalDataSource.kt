package com.aswingt.flightsearch.data.local.source

import com.aswingt.flightsearch.data.local.datastore.LocalSearchDataSource
import com.aswingt.flightsearch.data.local.room.FlightsDao

interface LocalDataSource : LocalSearchDataSource, FlightsDao