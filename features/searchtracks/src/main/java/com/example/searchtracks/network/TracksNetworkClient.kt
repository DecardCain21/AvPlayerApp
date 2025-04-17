package com.example.searchtracks.network

import com.example.core.network.RetrofitNetworkClient
import com.example.core.network.dto.TrackDto
import com.example.core.domain.models.Track

public interface TracksNetworkClient {
    public suspend fun getTracks(query: String): Result<List<Track>>
}

internal class TracksNetworkClientImpl(
    private val tracksApiService: TracksApiService
) : RetrofitNetworkClient(), TracksNetworkClient {
    override suspend fun getTracks(query: String): Result<List<Track>> {
        return super.doRequest {
            tracksApiService.getTracks(query).data.map { it.convertToTrack() }
        }
    }

    private fun TrackDto.convertToTrack(): Track = Track(
        id,
        readable,
        title,
        titleShort,
        titleVersion,
        link,
        duration,
        rank,
        explicitLyrics,
        explicitContentLyrics,
        explicitContentCover,
        preview,
        md5Image,
        artist.name,
        album.title,
        type
    )
}
