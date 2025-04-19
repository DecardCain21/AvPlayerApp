package com.example.player.domain.usecases.impl

import com.example.player.domain.models.Track
import com.example.player.data.api.PlaybackRepository
import com.example.player.domain.usecases.GetTrackByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetTrackByIdUseCaseImpl(
    private val playbackRepository: PlaybackRepository
) : GetTrackByIdUseCase {
    override suspend fun invoke(id: Long): Result<Track> = withContext(Dispatchers.IO) {
        playbackRepository.getTrack(id = id)
    }
}