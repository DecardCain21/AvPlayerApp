package com.example.player.data.network

import com.example.core.network.RetrofitNetworkClient
import com.example.player.data.dto.TrackDto

internal interface TracksNetworkClient {
    suspend fun getTracks(query: String): Result<List<TrackDto>>

    suspend fun getChart(): Result<List<TrackDto>>
}

internal class TracksNetworkClientImpl(
    private val tracksApiService: TracksApiService
) : RetrofitNetworkClient(), TracksNetworkClient {
    override suspend fun getTracks(query: String): Result<List<TrackDto>> {
        return super.doRequest {
            tracksApiService.getTracks(query).data
        }
    }

    override suspend fun getChart(): Result<List<TrackDto>> {
        return super.doRequest {
            tracksApiService.getChart().tracks.data
        }
    }

}
