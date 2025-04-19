package com.example.playback.data.api

import com.example.core.domain.models.Track

public interface PlaybackRepository {
    public suspend fun getTrack(id: Long): Result<Track>
}