package com.example.searchtracks.data.repository

import com.example.searchtracks.data.api.SearchTracksRepository
import com.example.core.domain.models.Track
import com.example.searchtracks.network.TracksNetworkClient

public class SearchTracksRepositoryImpl(
    private val tracksNetworkClient: TracksNetworkClient
) : SearchTracksRepository {
    override suspend fun getTracks(query: String): Result<List<Track>> {
        return tracksNetworkClient.getTracks(query = query)/*.map { it }*/
    }
}