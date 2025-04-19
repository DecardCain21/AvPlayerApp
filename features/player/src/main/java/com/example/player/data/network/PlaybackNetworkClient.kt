package com.example.player.data.network

import com.example.core.network.RetrofitNetworkClient
import com.example.player.data.dto.TrackDto

internal interface PlaybackNetworkClient {
    suspend fun getTrack(id: Long): Result<TrackDto>
}

internal class PlaybackNetworkClientImpl(
    private val getTrackByIdApiService: GetTrackByIdApiService
) : RetrofitNetworkClient(), PlaybackNetworkClient {
    override suspend fun getTrack(id: Long): Result<TrackDto> {
        return super.doRequest {
            getTrackByIdApiService.getTrackById(id = id)
        }
    }

}