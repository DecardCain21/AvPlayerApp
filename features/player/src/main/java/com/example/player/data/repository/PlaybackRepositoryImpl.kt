package com.example.player.data.repository

import com.example.player.domain.models.Track
import com.example.player.data.api.PlaybackRepository
import com.example.player.data.dto.convertToTrack
import com.example.player.data.network.PlaybackNetworkClient

internal class PlaybackRepositoryImpl(
    private val playbackNetworkClient: PlaybackNetworkClient
) : PlaybackRepository {
    override suspend fun getTrack(id: Long): Result<Track> {
        return playbackNetworkClient.getTrack(id = id)
            .map { trackDto -> trackDto.convertToTrack() }
    }
}