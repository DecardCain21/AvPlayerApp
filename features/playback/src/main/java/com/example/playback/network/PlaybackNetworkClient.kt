package com.example.playback.network

import com.example.core.domain.models.Track
import com.example.core.extantions.convertToTrack
import com.example.core.network.RetrofitNetworkClient

public interface PlaybackNetworkClient {
    public suspend fun getTrack(id: Long): Result<Track>
}

internal class PlaybackNetworkClientImpl(
    private val getTrackByIdApiService: GetTrackByIdApiService
) : RetrofitNetworkClient(), PlaybackNetworkClient {
    override suspend fun getTrack(id: Long): Result<Track> {
        return super.doRequest {
            getTrackByIdApiService.getTrackById(id = id).convertToTrack()
        }
    }

}