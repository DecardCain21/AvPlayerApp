package com.example.searchtracks.network

import com.example.core.network.RetrofitNetworkClient
import com.example.searchtracks.data.dto.TrackDto
import com.example.searchtracks.domain.models.Track

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
        title_short,
        title_version,
        link,
        duration,
        rank,
        explicit_lyrics,
        explicit_content_lyrics,
        explicit_content_cover,
        preview,
        md5_image,
        artist.name,
        album.title,
        type
    )
}
