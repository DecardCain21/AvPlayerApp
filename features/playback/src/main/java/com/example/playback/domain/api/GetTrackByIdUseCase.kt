package com.example.playback.domain.api

import com.example.core.domain.models.Track

public interface GetTrackByIdUseCase {
    public suspend operator fun invoke(id: Long): Result<Track>
}