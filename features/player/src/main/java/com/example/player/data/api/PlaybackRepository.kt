package com.example.player.data.api

import com.example.player.domain.models.Track

internal interface PlaybackRepository {
    suspend fun getTrack(id: Long): Result<Track>
}