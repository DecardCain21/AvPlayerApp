package com.example.player.data.repository

import com.example.player.domain.models.Track

import com.example.player.data.api.SearchTracksRepository
import com.example.player.data.dto.convertToTrack
import com.example.player.data.network.TracksNetworkClient

internal class SearchTracksRepositoryImpl(
    private val tracksNetworkClient: TracksNetworkClient
) : SearchTracksRepository {
    override suspend fun getTracks(query: String): Result<List<Track>> {
        return tracksNetworkClient.getTracks(query = query)
            .map { tracks -> tracks.map { trackDto -> trackDto.convertToTrack() } }
    }

    override suspend fun getChart(): Result<List<Track>> {
        return tracksNetworkClient.getChart()
            .map { tracks -> tracks.map { trackDto -> trackDto.convertToTrack() } }
    }
}