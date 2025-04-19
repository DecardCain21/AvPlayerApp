package com.example.searchtracks.network

import com.example.core.domain.models.Track
import com.example.core.extantions.convertToTrack
import com.example.core.network.RetrofitNetworkClient

public interface TracksNetworkClient {
    public suspend fun getTracks(query: String): Result<List<Track>>

    public suspend fun getChart(): Result<List<Track>>
}

internal class TracksNetworkClientImpl(
    private val tracksApiService: TracksApiService
) : RetrofitNetworkClient(), TracksNetworkClient {
    override suspend fun getTracks(query: String): Result<List<Track>> {
        return super.doRequest {
            tracksApiService.getTracks(query).data.map { it.convertToTrack() }
        }
    }

    override suspend fun getChart(): Result<List<Track>> {
        return super.doRequest {
            tracksApiService.getChart().tracks.data.map { it.convertToTrack() }
        }
    }

}
