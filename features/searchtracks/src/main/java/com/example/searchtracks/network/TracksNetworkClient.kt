package com.example.searchtracks.network

import com.example.core.domain.models.Track
import com.example.core.network.RetrofitNetworkClient
import com.example.core.network.dto.TrackDto

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

    private fun TrackDto.convertToTrack(): Track = Track(
        id = id,
        readable = readable ?: false,
        title = title ?: "",
        titleShort = titleShort ?: "",
        titleVersion = titleVersion ?: "",
        link = link ?: "",
        duration = duration ?: 0,
        rank = rank ?: 0,
        explicitLyrics = explicitLyrics ?: false,
        explicitContentLyrics = explicitContentLyrics ?: 0,
        explicitContentCover = explicitContentCover ?: 0,
        preview = preview ?: "",
        md5Image = md5Image ?: "",
        artistName = artist?.name ?: "",
        albumTitle = album?.title ?: "",
        type = type ?: ""
    )

}
