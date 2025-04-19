package com.example.player.data.api

import com.example.player.domain.models.Track

internal interface SearchTracksRepository {
    suspend fun getTracks(query: String): Result<List<Track>>

    suspend fun getChart(): Result<List<Track>>
}