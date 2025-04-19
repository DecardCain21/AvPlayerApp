package com.example.playback.data.repository

import com.example.core.domain.models.Track
import com.example.playback.data.api.PlaybackRepository
import com.example.playback.network.PlaybackNetworkClient

public class PlaybackRepositoryImpl(
    private val playbackNetworkClient: PlaybackNetworkClient
) : PlaybackRepository {
    override suspend fun getTrack(id: Long): Result<Track> {
        return playbackNetworkClient.getTrack(id = id)
    }
}