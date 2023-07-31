package com.aswingt.flightsearch.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface LocalSearchDataSource {

    suspend fun saveSearchQuery(query: String): Boolean

    fun getSearchQueryStream(): Flow<String>
}