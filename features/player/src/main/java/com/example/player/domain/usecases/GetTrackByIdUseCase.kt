package com.example.player.domain.usecases

import com.example.player.domain.models.Track

public interface GetTrackByIdUseCase {
    public suspend operator fun invoke(id: Long): Result<Track>
}